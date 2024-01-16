package org.ccs.app.core.user.application.usecase;

import org.ccs.app.core.user.domain.UserAccount;

public interface LoginUsecase {

    UserAccount login(String id, String password);
}
