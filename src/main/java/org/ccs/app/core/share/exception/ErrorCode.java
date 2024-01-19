package org.ccs.app.core.share.exception;

import lombok.Getter;

/**
 * Error Code 정리
 *  공용      : SHARE  (0-999)
 *  공통 도메인 : COMMON (1000-1999)
 *  유저 도메인 : USER   (2000-2999)
 *  (계속추가)
 */
public enum ErrorCode {
    // TODO : message 에 다국어 적용
    UNKNOWN(100, "", ""),

    UNAUTHORIZED_ACCESS(101, "사용자 인증에 실패하였습니다.", "Authentication failure."),
    NO_SUCH_USER(102, "로그인 아이디를 찾을 수 없습니다.", "Login ID not found."),
    INCORRECT_PASSWORD(103, "로그인 패스워드가 일치하지 않습니다.", "Incorrect login password."),

    NOT_FOUND_MENU(1000, "", ""),
    ;

    @Getter
    int code;

    @Getter
    String koMessage;

    @Getter
    String enMessage;


    ErrorCode(int code, String koMessage, String enMessage) {
        this.code = code;
        this.koMessage = koMessage;
        this.enMessage = enMessage;
    }
}
