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
        // 사용자 로그인 요청 유효성 검사
        validateLoginRequest(loginRequest);

        // 이메일 기반으로 사용자 계정 조회
        UserAccount userAccount = userRepository.findByEmail(loginRequest.getEmail());

        // 사용자 계정이 존재하는가? 비밀번호가 일치하는가?
        if (userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
            return tokenProvider.generateToken(userAccount.getId());
        } else { // 계정 정보가 잘못된 경우 예외처리
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}
