package com.ezen.mood.controller;

import com.ezen.mood.config.auth.LoginMember;
import com.ezen.mood.config.auth.dto.AuthDto;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.service.common.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class AuthController {

    private final MemberService memberService;


//    회원가입
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("authDto") AuthDto authDto, @LoginMember SessionMember member) {;
        if(member!=null){
            return "redirect:/";
        }
        return "auth/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("authDto") @Validated AuthDto authDto, BindingResult bindingResult) {
        // @Validated 에러 탐색
        if (bindingResult.hasErrors()) {
            return "auth/join";
        }

        if (memberService.isExist(authDto)) {
            bindingResult.reject("joinFail","이미 존재하는 이메일 입니다.");
            return "auth/join";
        }

        memberService.createAccount(authDto);

        return "redirect:/auth/login";
    }

//    로그인
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("authDto") AuthDto authDto, @LoginMember SessionMember member) {
        if(member!=null){
            return "redirect:/";
        }
        return "auth/login";
    }


    @GetMapping("/denied")
    public String deniedForm() {
        return "auth/denied";
    }


}
