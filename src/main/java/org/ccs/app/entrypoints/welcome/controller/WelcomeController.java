package org.ccs.app.entrypoints.welcome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/dashboard")
    public String welcome() {

        return "index";
    }
}