package org.ccs.app.core.share.exception.auth;

import org.ccs.app.core.share.exception.BusinessException;
import org.ccs.app.core.share.exception.ErrorCode;

public class UnauthorizedAccessException extends BusinessException {
    public UnauthorizedAccessException(int code, String message) {
        super(code, message);
    }

    public UnauthorizedAccessException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public UnauthorizedAccessException() {
        this(ErrorCode.UNAUTHORIZED_ACCESS);
    }
}