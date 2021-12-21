package com.ezen.mood.controller;

import com.ezen.mood.config.auth.dto.LoginDto;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final MemberService memberService;
    private final HttpSession httpSession;

    @PostMapping("/doin")
    public String login(@ModelAttribute("member") @Validated LoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member member = memberService.checkLogin(loginDto);
        if (member == null) {
            bindingResult.reject("loginFail","아이디 또는 패스워드가 올바르지 않습니다");
            return "login/loginForm";
        }

        SessionMember sessionMember = member.toSessionMember();

        httpSession.setAttribute("member",sessionMember);

        return "redirect:/";
    }

    @GetMapping("/doin")
    public String loginForm(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "login/loginForm";
    }
}
