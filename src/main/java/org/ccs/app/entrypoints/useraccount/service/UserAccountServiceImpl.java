package org.ccs.app.entrypoints.useraccount.service;

import lombok.AllArgsConstructor;
import org.ccs.app.core.user.domain.User;
import org.ccs.app.core.user.domain.UserAccount;
import org.ccs.app.core.user.infra.repository.UserAccountRepository;
import org.ccs.app.entrypoints.useraccount.model.UserAccountRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    @Transactional
    public Long signup(UserAccountRequest userAccountRequest) {
        User user = User.builder()
                .name(userAccountRequest.getUsername())
                .phoneNumber(userAccountRequest.getPhoneNumber())
                .address(userAccountRequest.getAddress())
                .gender(userAccountRequest.getGender())
                .parish(userAccountRequest.getParish())
                .cellGroup(userAccountRequest.getCellGroup())
                .zipcode(userAccountRequest.getZipcode())
                .build();

        UserAccount userAccount = UserAccount.builder()
                .email(userAccountRequest.getEmail())
                .password(userAccountRequest.getPassword())
                .user(user)
                .build();

        userAccount = userAccountRepository.save(userAccount);
        return userAccount.getId();
    }
}
