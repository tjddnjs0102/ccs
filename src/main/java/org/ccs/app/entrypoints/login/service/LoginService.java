package org.ccs.app.entrypoints.login.service;

import lombok.RequiredArgsConstructor;
import org.ccs.app.core.security.JwtTokenProvider;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserRepository;
import org.ccs.app.entrypoints.login.model.JwtAuthenticationResponse;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 자동으로 생성
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    private UserAccount findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String authenticate(LoginRequest loginRequest) {
        UserAccount userAccount = findUserByEmail(loginRequest.getEmail());

        if (userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
            return tokenProvider.generateToken(userAccount.getId());
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    // 리프레시 토큰 생성 메서드
    public String createRefreshToken(LoginRequest loginRequest) {
        UserAccount userAccount = findUserByEmail(loginRequest.getEmail());

        if (userAccount != null) {
            return tokenProvider.generateRefreshToken(userAccount.getId());
        } else {
            throw new IllegalArgumentException("User not found with email: " + loginRequest.getEmail());
        }
    }

    public JwtAuthenticationResponse authenticateAndCreateTokens(LoginRequest loginRequest) {
        UserAccount userAccount = findUserByEmail(loginRequest.getEmail());

        if (userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
            String jwt = tokenProvider.generateToken(userAccount.getId());
            String refreshToken = tokenProvider.generateRefreshToken(userAccount.getId());

            return new JwtAuthenticationResponse(jwt, refreshToken);
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}
