package org.ccs.app.entrypoints.login.service;

import lombok.RequiredArgsConstructor;
import org.ccs.app.core.share.authenticate.token.JWTType;
import org.ccs.app.core.share.authenticate.token.JWTUtil;
import org.ccs.app.core.user.application.usecase.LoginUsecase;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.entrypoints.login.model.JwtAuthenticationResponse;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoginService {
    private final Logger log = LoggerFactory.getLogger(LoginService.class);

    private final LoginUsecase loginUsecase;
    private final JWTUtil jwtUtil;

    public String authenticate(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.debug("[authenticated] account: {}", account);

        return jwtUtil.generate(JWTType.ACCESS, account.getId());
    }

    public String createRefreshToken(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.debug("[refresh token created] account: {}", account);
        return jwtUtil.generate(JWTType.REFRESH, account.getId());
    }

    public JwtAuthenticationResponse authenticateAndCreateTokens(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        String jwt = jwtUtil.generate(JWTType.ACCESS, account.getId());
        String refreshToken = jwtUtil.generate(JWTType.REFRESH, account.getId());

        return new JwtAuthenticationResponse(jwt, refreshToken);
    }
}
