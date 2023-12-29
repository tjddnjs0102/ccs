package org.ccs.app.core.common.exception;

import org.ccs.app.core.share.domain.ErrorCode;
import org.ccs.app.core.share.exception.BusinessException;

public class NotFoundMenuItemException extends BusinessException {

    public NotFoundMenuItemException() {
        super(ErrorCode.NOT_FOUND_MENU.getCode(), ErrorCode.NOT_FOUND_MENU.getMessage());
    }
}
