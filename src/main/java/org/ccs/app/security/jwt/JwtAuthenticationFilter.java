package org.ccs.app.security.jwt;

// 인증 요청을 가로채 JWT의 '유효성'을 검사
// Spring Security의 OncePerRequestFilter를 상속받아 구현할 수 있으며, 요청의 헤더에서 JWT를 추출하고 JwtProvider를 통해 검증

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // JWT 처리 로직

        filterChain.doFilter(request, response);
    }
}
