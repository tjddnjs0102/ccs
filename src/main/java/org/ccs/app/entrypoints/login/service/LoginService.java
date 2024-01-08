package org.ccs.app.entrypoints.login.service;

import org.ccs.app.core.security.JwtTokenProvider;
import org.ccs.app.core.share.utils.ValidationUtils;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserRepository;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public String authenticate(LoginRequest loginRequest) {
        // 이메일을 기반으로 사용자 계정 조회
        UserAccount userAccount = userRepository.findByEmail(loginRequest.getEmail());

        // 사용자 계정이 존재하고 비밀번호가 일치하는지 확인
        if (userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
            // JWT 토큰 생성 및 반환
            return tokenProvider.generateToken(userAccount.getId());
        } else {
            // 잘못된 이메일 또는 비밀번호에 대한 예외 처리
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}
