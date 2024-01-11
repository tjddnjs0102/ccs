package org.ccs.app.core.security.infra;

import org.ccs.app.core.security.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // 리프레시 토큰에 관련된 데이터베이스 조작 메소드 추가
    Optional<RefreshToken> findByToken(String token);
}
