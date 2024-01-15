package org.ccs.app.core.share.exception.auth;

import org.ccs.app.core.share.exception.ErrorCode;

public class PasswordNotMatchException extends UnauthorizedAccessException {

    public PasswordNotMatchException() {
        super(ErrorCode.INCORRECT_PASSWORD);
    }
}
