package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.ccs.app.core.share.authenticate.Authenticate;
import org.ccs.app.core.share.authenticate.AuthenticateHolder;
import org.ccs.app.core.share.authenticate.token.JWTUtil;
import org.ccs.app.core.share.exception.BusinessException;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
@Order(20)
@RequiredArgsConstructor
@Component
public class JWTFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(JWTFilter.class);
    private static final String AUTHORIZATION = "Authorization";

    private final JWTUtil jwtUtil;
    private final UserAccountRepository userAccountRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String token = httpServletRequest.getHeader(AUTHORIZATION);
        log.debug("[authorization] {}", token);

        boolean result = this.setAuthenticate(token);

        if (!result) {
            // TODO: 여기에선 BusinessException 을 던지면 안됩니다. 이 부분을 구현해주세요.
            throw new BusinessException();
        }

        chain.doFilter(request, response);
        AuthenticateHolder.clear();
    }

    private boolean setAuthenticate(String token) {
        if (Objects.isNull(token)) {
            AuthenticateHolder.setAuthenticate(new Authenticate());
            return true;
        }

        if (!token.startsWith("Bearer ")) return false;

        String replacedToken = token.substring(7);
        if (!jwtUtil.verify(replacedToken)) return false;

        Long accountId = (Long) jwtUtil.decode(replacedToken);
        log.debug("[account]: {}", accountId);

        // TODO: 여기에선 BusinessException 을 던지면 안됩니다. 이 부분을 구현해주세요.
        UserAccount account = userAccountRepository.findById(accountId).orElseThrow(BusinessException::new);

        AuthenticateHolder.setAuthenticate(new Authenticate(account));
        return true;
    }
}
