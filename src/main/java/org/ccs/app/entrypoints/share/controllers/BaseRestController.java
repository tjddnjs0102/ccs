package org.ccs.app.entrypoints.share.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

/**
 * Rest Controller 구현용
 */
public interface BaseRestController {
    Logger log = LoggerFactory.getLogger(BaseRestController.class);
    default void hasError(BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            return;
        }



    }
}
