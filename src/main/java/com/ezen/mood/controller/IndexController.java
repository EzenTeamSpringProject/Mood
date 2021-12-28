package com.ezen.mood.controller;

import com.ezen.mood.config.auth.LoginMember;
import com.ezen.mood.config.auth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, @LoginMember SessionMember member) {
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "index";
    }
}
