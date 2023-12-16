package org.ccs.app.security;

// Spring Security 설정을 정의
// WebSecurityConfigurerAdapter를 상속받아 구현
// JwtAuthenticationFilter 및 JwtAuthorizationFilter를 보안 체인에 추가

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll() // /public/(version)
                        .requestMatchers("/api/**").authenticated() // 토큰O
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // 토큰O + 권한O
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
