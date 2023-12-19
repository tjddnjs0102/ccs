package org.ccs.app.security.service;

import org.ccs.app.core.user.domain.Role;
import org.ccs.app.core.user.domain.User;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.domain.UserRole;
import org.ccs.app.core.user.infra.repository.UserAccountRepository;
import org.ccs.app.core.user.infra.repository.UserRepository;
import org.ccs.app.security.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

// Spring Security에서 사용자 인증 정보를 불러오는 서비스를 정의
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // UserAccountRepository 인터페이스를 통해 UserAccount 엔티티에 접근
    @Autowired
    private UserAccountRepository userAccountRepository;

    // 사용자 이름을 기반으로 사용자 정보를 조회하고 UserDetails 객체를 반환
    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일로 UserAccount를 조회
        UserAccount account = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " +   email));

        // 조회된 UserAccount 정보를 바탕으로 CustomUserDetails 객체를 생성
        return new CustomUserDetails(
                account.getEmail(),
                account.getPassword(),
                account.getRoles().stream() // 사용자에게 할당된 역할을 기반으로 권한을 설정
                        .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                        .collect(Collectors.toList()),
                account.getStatus(),
                account.getRoles().stream()
                        .map(UserRole::getRole)
                        .collect(Collectors.toSet())
        );
    }
}
