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

// TODO: 혹시나 불필요한 부분이 있다면 제거해주세요. 개선할 수 있는 내용이 있다면 변경해도 좋습니다.!!

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

    // TODO: JWTUtil을 활용해서 코드를 수정하세요.
    public String createRefreshToken(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.debug("[refresh token created] account: {}", account);
        return jwtUtil.generateRefreshToken(account.getId());
    }

    // TODO: JWTUtil을 활용해서 코드를 수정하세요.
    public JwtAuthenticationResponse authenticateAndCreateTokens(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        String jwt = jwtUtil.generateToken(account.getId());
        String refreshToken = jwtUtil.generateRefreshToken(account.getId());

        return new JwtAuthenticationResponse(jwt, refreshToken);
    }
}
