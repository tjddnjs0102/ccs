package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@WebFilter("/admin/*")
@Order(31)
public class AdminContextFilter implements Filter {

    // TODO: 로직을 작성하세요.
    // 힌트 : threadlocal 에 담겨있는 authenticate 객체를 활용하면 됩니다. 거기서 role 정보를 꺼내서 확인하세요.

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
