package org.ccs.app.core.user.application;

import lombok.RequiredArgsConstructor;
import org.ccs.app.core.share.exception.auth.NoSuchUserException;
import org.ccs.app.core.share.exception.auth.PasswordNotMatchException;
import org.ccs.app.core.user.application.usecase.LoginUsecase;
import org.ccs.app.core.user.application.usecase.LogoutUsecase;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserAccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AuthenticationApplication implements LoginUsecase, LogoutUsecase {
    private final UserAccountRepository userAccountRepository;

    @Transactional(noRollbackFor = PasswordNotMatchException.class) // 엄청중요함!! 이것이 어떤 역할인지 공부하세요!!
    @Override
    public UserAccount login(String id, String password) {
        UserAccount account = getUserByEmail(id);
        account.confirmPassword(password);
        return account;
    }

    @Override
    public void logout() {
        // FIXME: 나중에 구현할 기능
    }

    // README에 getXXX(), findXXXX() 메소드의 차이를 적어두었습니다.
    private UserAccount getUserByEmail(String email) {
        return userAccountRepository.findByEmail(email).orElseThrow(NoSuchUserException::new);
    }
}
