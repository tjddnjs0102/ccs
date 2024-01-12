package org.ccs.app.entrypoints.login.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long expiresIn; // 토큰 만료 시간을 나타내는 필드
}
