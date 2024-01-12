package org.ccs.app.entrypoints.share.controller;

import org.ccs.app.entrypoints.share.exception.InvalidRequestParameterException;
import org.ccs.app.entrypoints.share.model.ContentBody;
import org.ccs.app.entrypoints.share.model.ErrorBindings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class BaseRestControllerAdvisor {
    private final Logger LOG = LoggerFactory.getLogger(BaseRestControllerAdvisor.class);

    @ExceptionHandler(InvalidRequestParameterException.class)
    public ContentBody<List<ErrorBindings>> handle(InvalidRequestParameterException e) {
        LOG.warn("Invalid request parameters: {}", e);
        return new ContentBody<>(400, "Bad request.", "", e.getErrors());
    }

    @ExceptionHandler(Exception.class)
    public ContentBody handleException(Exception e) {
        LOG.error("Error가 발생하였습니다. {}", e);
        return new ContentBody(500, "Unknown error.", "", null);
    }
}
