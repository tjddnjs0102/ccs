package org.ccs.app.entrypoints.useraccount.model;

import lombok.Getter;

// record 전환 가능
@Getter
public class UserAccountResponse {
    private final String username;
    private final String email;

    public UserAccountResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
