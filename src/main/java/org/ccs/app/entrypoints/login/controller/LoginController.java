package org.ccs.app.entrypoints.login.controller;

import org.ccs.app.core.security.JwtTokenProvider;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserRepository;
import org.ccs.app.entrypoints.login.model.JwtAuthenticationResponse;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            validateLoginRequest(loginRequest);

            // 요청 본문에서 로그인 정보를 받아 UserAccount 객체 조회
            UserAccount userAccount = userRepository.findByEmail(loginRequest.getEmail());
            // 사용자 계정이 존재하고 비밀번호가 일치하는가?
            if (userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
                // 인증성공하면 사용자 ID 사용하여 JWT 토큰 생성
                String jwt = tokenProvider.generateToken(userAccount.getId());

                // 생성된 JWT 토큰을 응답 본문에 담아 반환
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            } else {
                // 인증실패 시, badRequest 응답 반환
                return ResponseEntity.badRequest().body("Invalid email or password");
            }
        } catch (Exception e) {
            // 기타 에외처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    private void validateLoginRequest(LoginRequest loginRequest) {
        // TODO: 입력 값 검증 로직 입력 (이메일 형식, 비밀번호 길이 등)
    }
}
