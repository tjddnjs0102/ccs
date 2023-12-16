package org.ccs.app.security.jwt;

// 인증 요청을 가로채 JWT의 '유효성'을 검사
// Spring Security의 OncePerRequestFilter를 상속받아 구현할 수 있으며, 요청의 헤더에서 JWT를 추출하고 JwtProvider를 통해 검증
public class JwtAuthenticationFilter {
}
