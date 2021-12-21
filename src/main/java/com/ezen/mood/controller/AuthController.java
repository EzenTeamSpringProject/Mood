package com.ezen.mood.controller;

import com.ezen.mood.config.auth.dto.AuthDto;
import com.ezen.mood.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class AuthController {

    private final MemberService memberService;


//    회원가입
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("authDto") AuthDto authDto, HttpServletRequest request) {
        if(request.getSession(false)!=null){
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
    public String loginForm(@ModelAttribute("authDto") AuthDto authDto,HttpServletRequest request) {
        if(request.getSession(false)!=null){
            return "redirect:/";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("authDto") @Validated AuthDto authDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        return "auth/login";
    }


    @GetMapping("/denied")
    public String deniedForm() {
        return "auth/denied";
    }


//    @PostMapping("/login")
//    public String login(@ModelAttribute @Validated AuthDto authDto, BindingResult bindingResult, HttpServletRequest request) {
////        if (bindingResult.hasErrors()) {
////            return "login/login";
////        }
////        System.out.println("1번");
////        Member member = memberService.checkLogin(authDto);
////        System.out.println("2번");
////        if (member == null) {
////            bindingResult.reject("loginFail","아이디 또는 패스워드가 올바르지 않습니다");
////            System.out.println("멤버가 없음");
////            return "login/login";
////        }
////        System.out.println("3번");
////        SessionMember sessionMember = member.toSessionMember();
////        System.out.println("4번");
////        request.getSession().setAttribute("member",sessionMember);
////        System.out.println("5번");
//        return "redirect:/";
//    }

}
