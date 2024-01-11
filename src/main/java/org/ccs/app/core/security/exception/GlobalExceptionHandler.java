package org.ccs.app.core.security.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // SLF4J Logger 인스턴스를 생성합니다. 이 로거는 이 클래스 내에서 발생하는 모든 로그를 처리합니다.
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // @ExceptionHandler 어노테이션은 특정 예외가 발생했을 때 이 메서드가 처리하도록 지정합니다.
    // 여기서는 ResponseStatusException 타입의 예외를 처리합니다.
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // 예외가 발생했을 때, 에러 메시지를 로그로 기록합니다.
        logger.error("Error occurred: {}", ex.getMessage());

        // 클라이언트에게 HTTP 상태 코드와 에러 메시지를 담은 ResponseEntity를 반환합니다.
        // ex.getStatusCode()는 예외에 설정된 HTTP 상태 코드를 가져옵니다.
        // ex.getMessage()는 예외에 설정된 메시지를 가져옵니다.
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}
