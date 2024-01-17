package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ccs.app.core.share.authenticate.Authenticate;
import org.ccs.app.core.share.authenticate.AuthenticateHolder;
import org.ccs.app.core.user.domain.Role;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@WebFilter("/admin/*")
@Order(31)
public class AdminContextFilter implements Filter {

    Authenticate authenticate = AuthenticateHolder.getLoginUser();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (authenticate != null && authenticate.getRoles().contains(Role.ADMIN)) {
            chain.doFilter(request, response);
        }else { // ADMIN 권한이 없는 경우
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            httpResponse.getWriter().write("Access Denied: Insufficient Permissions");
        }
    }
}