package org.ccs.app.entrypoints.useraccount.controller;

import lombok.AllArgsConstructor;
import org.ccs.app.entrypoints.useraccount.model.UserAccountRequest;
import org.ccs.app.entrypoints.useraccount.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
public class UserAccountController {

    private UserAccountService userAccountService;
    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @GetMapping("/")
    public String index() {
        return "/home/index"; // 홈페이지 경로 임의로 적어두었습니다. 확인 후 수정 필요
    }

    @GetMapping("/public/account/signup")
    public String signupForm(Model model) {
        model.addAttribute("userAccount", new UserAccountRequest());
        return "/public/account/signupForm";
    }

    @PostMapping("/public/account/signup")
    public String signup(@Validated @RequestBody UserAccountRequest userAccountRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/public/account/signupForm";
        }

        userAccountService.signup(userAccountRequest);
        logger.info("회원가입 성공: {}", userAccountRequest.getUsername());
        return "redirect:/";
    }
}
