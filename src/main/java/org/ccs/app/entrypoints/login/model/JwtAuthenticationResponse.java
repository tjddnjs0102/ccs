package org.ccs.app.entrypoints.login.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
}
