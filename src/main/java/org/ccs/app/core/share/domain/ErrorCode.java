package org.ccs.app.core.share.domain;

import lombok.Getter;

/**
 * Error Code 정리
 *  공용      : SHARE  (0-999)
 *  공통 도메인 : COMMON (1000-1999)
 *  유저 도메인 : USER   (2000-2999)
 *  (계속추가)
 */
public enum ErrorCode {

    NOT_FOUND_MENU(1000, ""),
    ;

    @Getter
    int code;

    @Getter
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
