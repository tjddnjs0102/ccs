package org.ccs.app.core.share.exception.auth;

import org.ccs.app.core.share.exception.ErrorCode;

public class NoSuchUserException extends UnauthorizedAccessException {

    public NoSuchUserException() {
        super(ErrorCode.NO_SUCH_USER);
    }
}
