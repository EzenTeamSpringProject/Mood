package com.ezen.mood.controller.member;

import com.ezen.mood.config.auth.LoginMember;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.member.WishContent;
import com.ezen.mood.service.common.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     *    /contents
     */
    @GetMapping("/contents")
    public String myPage(@LoginMember SessionMember smember, Model model) {
        model.addAttribute("category","wishContent");
        String email = smember.getEmail();
        Member member = memberService.findByEmail(email);
        List<WishContent> wishes = member.getWishes();
        List<Content> contents = wishes.stream().map(wishContent -> wishContent.getContent()).collect(Collectors.toList());
        model.addAttribute("contents",contents);
        return "member/myPage";
    }

    /**
     * /reviews
     */
    @GetMapping("/member/reviews/create")
    public String createReview() {
        return "";
    }


    /**
     *    /info
     */
}
