package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class ApiContextFilter implements Filter {

    // TODO: 로직을 작성하세요.
    // 힌트 : threadlocal 에 담겨있는 authenticated 를 확인해서 처리할 수 있습니다.

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
