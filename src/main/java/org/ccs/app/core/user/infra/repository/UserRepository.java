package org.ccs.app.core.user.infra.repository;

import org.ccs.app.core.user.domain.User;
import org.ccs.app.core.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 메소드 쿼리를 정의한다.
 */

// TODO : DB 연결 후 수정
@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>, UserCustomRepository {
    UserAccount findByEmail(String email);
}
