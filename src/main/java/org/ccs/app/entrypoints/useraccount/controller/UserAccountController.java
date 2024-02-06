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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
public class UserAccountController {

    private UserAccountService userAccountService;
    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @GetMapping("/")
    public String index(Model model) {
        if (model.containsAttribute("signupSuccess")) {
            model.addAttribute("message", model.asMap().get("signupSuccess"));
        }
        return "/index";
    }

    @GetMapping("/public/account/signup")
    public String signupForm(Model model) {
//        model.addAttribute("userAccount", new UserAccountRequest());
//        return "/public/account/signupForm";
        return "under-construction"; // 준비중 페이지로 매핑
    }

    @PostMapping("/public/account/signup")
    public String signup(@Validated @RequestBody UserAccountRequest userAccountRequest, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/public/account/signupForm";
        }

        userAccountService.signup(userAccountRequest);
        logger.info("회원가입 성공: {}", userAccountRequest.getUsername());
        redirectAttributes.addFlashAttribute("signupSuccess", "회원가입이 성공적으로 완료되었습니다.");
        return "redirect:/";
    }
}
