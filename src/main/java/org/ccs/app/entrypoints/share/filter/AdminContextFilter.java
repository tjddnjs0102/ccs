package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ccs.app.core.share.authenticate.Authenticate;
import org.ccs.app.core.share.authenticate.AuthenticateHolder;
import org.ccs.app.core.user.domain.Role;
import org.ccs.app.entrypoints.share.model.ContentBody;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@WebFilter("/admin/*")
@Order(31)
public class AdminContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authenticate authenticate = AuthenticateHolder.get();

        if (!hasAdminRole(authenticate)) {
            sendAccessDeniedResponse(response);
        }

        chain.doFilter(request, response);
    }

    private boolean hasAdminRole(Authenticate authenticate) {
        return authenticate != null && authenticate.getRoles().contains(Role.ADMIN);
    }

    private void sendAccessDeniedResponse(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ContentBody<String> accessDeniedBody = new ContentBody<>(
                HttpServletResponse.SC_FORBIDDEN,
                "Access Denied: 관리자 권한이 필요합니다.",
                "",
                null);

        String serializedBody = ContentBody.serialize(accessDeniedBody);
        httpResponse.setContentType("application/json");
        httpResponse.getWriter().write(serializedBody);
    }
}