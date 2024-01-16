package org.ccs.app.entrypoints.login.service;

import lombok.RequiredArgsConstructor;
import org.ccs.app.core.security.JwtTokenProvider;
import org.ccs.app.core.share.exception.auth.NoSuchUserException;
import org.ccs.app.core.user.application.usecase.LoginUsecase;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserAccountRepository;
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
    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider tokenProvider;

    public String authenticate(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.debug("[authenticated] account: {}", account);
        return tokenProvider.generateToken(account.getId());
    }

    // 리프레시 토큰 생성 메서드
    public String createRefreshToken(LoginRequest loginRequest) {
        UserAccount account = loginUsecase.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.debug("[refresh token created] account: {}", account);
        return tokenProvider.generateRefreshToken(account.getId());
    }

    public JwtAuthenticationResponse authenticateAndCreateTokens(LoginRequest loginRequest) {
        UserAccount userAccount = getUserByEmail(loginRequest.getEmail());

        if (userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
            String jwt = tokenProvider.generateToken(userAccount.getId());
            String refreshToken = tokenProvider.generateRefreshToken(userAccount.getId());

            return new JwtAuthenticationResponse(jwt, refreshToken);
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    private UserAccount getUserByEmail(String email) {
        return userAccountRepository.findByEmail(email)
                .orElseThrow(NoSuchUserException::new);
    }
}
