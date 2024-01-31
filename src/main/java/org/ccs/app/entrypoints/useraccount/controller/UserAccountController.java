package org.ccs.app.entrypoints.useraccount.controller;

import lombok.AllArgsConstructor;
import org.ccs.app.entrypoints.useraccount.model.UserAccountRequest;
import org.ccs.app.entrypoints.useraccount.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserAccountController {

    private UserAccountService userAccountService;

    @GetMapping("/")
    public String index() {
        return "/home/index"; // 홈페이지 경로 임의로 적어두었습니다. 확인 후 수정 필요
    }

    @GetMapping("/public/member/signup")
    public String signupForm(Model model) {
        model.addAttribute("userAccount", new UserAccountRequest());

        return "/public/member/signupForm";
    }

    @PostMapping("/public/member/signup")
    public String signup(UserAccountRequest userAccountRequest) {
//        userAccountService.signUp(userAccountRequest); // 회원가입 로직 수행 (아직 서비스 미구현)

        return "redirect:/";
    }
}
