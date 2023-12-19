package org.ccs.app.security.jwt;

// 사용자의 요청이 특정 '권한'을 가진 사용자에게만 허용되는지 확인
// Spring Security의 Filter를 상속받아 구현하며, 사용자의 권한을 검증

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        // 권한 처리 로직


        chain.doFilter(request, response);
    }
}
