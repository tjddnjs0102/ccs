package org.ccs.app.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ccs.app.core.user.domain.AccountStatus;
import org.ccs.app.core.user.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // 모든 필드를 포함하는 생성자 생성
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private AccountStatus accountStatus; // 사용자의 계정 상태를 나타내는 필드
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정만료 여부
        return accountStatus != AccountStatus.BLOCKED;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정잠김 여부
        return accountStatus != AccountStatus.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 사용자의 자격 증명(비밀번호) 만료 여부
        return true; // 구체적인 로직이 필요할 경우 추가
    }

    @Override
    public boolean isEnabled() { // 계정활성화 여부
        return accountStatus == AccountStatus.ENABLED;
    }
}
