package org.ccs.app.entrypoints.share.entrypoints.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

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
