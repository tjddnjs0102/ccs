package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.ccs.app.core.share.authenticate.Authenticate;
import org.ccs.app.core.share.authenticate.AuthenticateHolder;
import org.ccs.app.core.share.authenticate.token.JWTUtil;
import org.ccs.app.core.share.exception.BusinessException;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class JWTFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(JWTFilter.class);
    private static final String AUTHORIZATION = "Authorization";
    private static final String[] exclude = {"/login", "/public"};

    private JWTUtil jwtUtil;
    private UserAccountRepository userAccountRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        this.jwtUtil = webApplicationContext.getBean(JWTUtil.class);
        this.userAccountRepository = webApplicationContext.getBean(UserAccountRepository.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String token = httpServletRequest.getHeader(AUTHORIZATION);
        log.debug("[authorization] {}", token);

        boolean result = this.setAuthenticate(token, exclusionRequestPath(httpServletRequest));

        if (!result) {
            // TODO: 여기에선 BusinessException 을 던지면 안됩니다. 이 부분을 구현해주세요.
            throw new BusinessException();
        }

        chain.doFilter(request, response);
        AuthenticateHolder.clear();
    }

    private boolean exclusionRequestPath(HttpServletRequest request) {
        return Arrays.stream(exclude)
                .anyMatch(it -> request.getRequestURI().startsWith(it));
    }

    /**
     * 토큰에 대한 검증을 한다.
     * 인증에 제외 될 path 이면서 토큰이 없는 경우 new Authenticate() 한다.
     * 인증에 제외 될 path 이나 토큰이 존재 한다면 토큰에 대한 검증을 한다.
     * 인증에 제외 된 path 가 아닌데 토큰이 없다면 인증에 실패한다.
     * @param token
     * @param excluded : 인증에 제외 된 요청여부 (true - 제외, false - 인증대상)
     * @return
     */
    private boolean setAuthenticate(String token, boolean excluded) {
        if (excluded && Objects.isNull(token)) {
            AuthenticateHolder.setAuthenticate(new Authenticate());
            return true;
        }

        if (Objects.isNull(token))
            return false;

        if (!token.startsWith("Bearer "))
            return false;

        String replacedToken = token.substring(7);
        if (!jwtUtil.verify(replacedToken))
            return false;

        Long accountId = (Long) jwtUtil.decode(replacedToken);
        log.debug("[account]: {}", accountId);

        // TODO: 여기에선 BusinessException 을 던지면 안됩니다. 이 부분을 구현해주세요.
        UserAccount account = userAccountRepository.findById(accountId).orElseThrow(BusinessException::new);

        AuthenticateHolder.setAuthenticate(new Authenticate(account));
        return true;
    }


}
