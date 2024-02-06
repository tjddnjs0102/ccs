package org.ccs.app.entrypoints.useraccount.service;


import org.ccs.app.entrypoints.useraccount.model.UserAccountRequest;

public interface UserAccountService {
    Long signup(UserAccountRequest userAccountRequest);
}

