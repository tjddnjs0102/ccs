package org.ccs.app.entrypoints.share.controllers;

import org.ccs.app.entrypoints.share.model.ContentBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {
    private final Logger LOG = LoggerFactory.getLogger(RestControllerAdvisor.class);

    @ExceptionHandler(Exception.class)
    public ContentBody<String> handleException(Exception e) {
        LOG.error("Error가 발생하였습니다.");
        return null;
    }
}
