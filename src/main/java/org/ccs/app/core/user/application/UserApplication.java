package org.ccs.app.core.user.application;

import org.ccs.app.core.user.application.usecase.CreateUserUsecase;
import org.ccs.app.core.user.application.usecase.UpdateUserUsecase;
import org.springframework.stereotype.Component;

@Component
public class UserApplication implements
        CreateUserUsecase,
        UpdateUserUsecase {

    @Override
    public void create(CreateUserCommnad commnad) {

    }

    @Override
    public void update(UpdateUserCommand command) {

    }
}
