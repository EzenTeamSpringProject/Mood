package com.ezen.mood.controller.member;

import com.ezen.mood.config.auth.LoginMember;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.member.WishContent;
import com.ezen.mood.domain.review.Review;
import com.ezen.mood.domain.review.WishReview;
import com.ezen.mood.dto.ReviewFormDto;
import com.ezen.mood.repository.WishContentRepository;
import com.ezen.mood.repository.WishReviewRepository;
import com.ezen.mood.service.common.MemberService;
import com.ezen.mood.service.member.ReviewService;
import com.ezen.mood.util.PosterUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final PosterUtilities posterUtilities;
    private final WishReviewRepository wishReviewRepository;
    private final WishContentRepository wishContentRepository;





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
        return "member/myPageContent";
    }


    /**
     * 리뷰
     */
// 리뷰 등록
    @GetMapping("/reviews/write")
    public String writeReviewForm(@ModelAttribute("dto") ReviewFormDto dto) {
        return "member/reviewForm";
    }

    @PostMapping("/reviews/write")
    public String writeReview(@ModelAttribute("dto") ReviewFormDto dto, @LoginMember SessionMember smember, RedirectAttributes redirectAttributes) throws IOException {
        reviewService.saveReview(dto,smember);
        return "redirect:/member/reviews";
    }

    // 리뷰 수정
    @GetMapping("/reviews/{id}/edit")
    public String editReviewForm(@PathVariable Long id,Model model) {
        Review review = reviewService.findById(id);
        ReviewFormDto dto = review.toFormDto();
        model.addAttribute("dto",dto);
        return "member/reviewForm";
    }

    @PostMapping("/reviews/{id}/edit")
    public String editReview(@PathVariable Long id,@ModelAttribute("dto") ReviewFormDto dto) throws IOException {
        reviewService.updateReview(id,dto);
        return "redirect:/member/reviews";
    }

//   리뷰 삭제

    @GetMapping("/reviews/{id}/delete")
    public String deleteReview(@PathVariable Long id, Model model) {
        reviewService.deleteReview(id);
        message(model, "컨텐츠가 삭제되었습니다.", "admin/contents");
        return "message";
    }



    // 내  리뷰 조회
    @GetMapping("/reviews")
    public String findMyReview(@LoginMember SessionMember smember, Model model) {
        List<Review> myReviews = reviewService.findAllOfMyReview(smember);
        model.addAttribute("contents",myReviews);
        model.addAttribute("category","myReview");
        return "member/myPageReview";
    }
// 내가 찜한 리뷰 조회
    @GetMapping("/reviews/other")
    public String wishReviews(@LoginMember SessionMember smember,Model model) {
        Member member = memberService.findByEmail(smember.getEmail());
        List<Review> reviews = member.getWishReviews().stream().map(wishReview -> wishReview.getReview()).collect(Collectors.toList());
        model.addAttribute("contents",reviews);
        model.addAttribute("category","wishReview");
        return "member/myPageReview";
    }


    /**
     * 멤버 인포
     */

    @GetMapping("/info")
    public String memberInfo(Model model) {
        model.addAttribute("category","myInfo");
        return "member/myInfo";
    }

    @PostMapping("/info/edit")
    public String editInfo(@RequestParam String name, @RequestParam String password, @LoginMember SessionMember smember, HttpServletRequest request,Model model) {
        Member member = memberService.findByEmail(smember.getEmail());
        if (member.getPassword() == null) {
            message(model,"타 플랫폼 로그인은 개인정보 변경이 불가능합니다.","member/info");
            return "message";
        }
        member.setName(name);
        member.setName(password);
        SessionMember sessionMember = new SessionMember(member);
        HttpSession session = request.getSession(false);
        session.setAttribute("member",sessionMember);
        message(model,"변경되었습니다.","member/info");

        return "message";
    }

    @GetMapping("/info/edit/image/default")
    public String imageDefault(@LoginMember SessionMember smember,HttpServletRequest request,Model model) {
        Member member = memberService.findByEmail(smember.getEmail());
        if (member.getPassword() == null) {
            message(model,"타 플랫폼 로그인은 개인정보 변경이 불가능합니다.","member/info");
            return "message";
        }
        member.setPicture("/img/defaultProfile.png");
        SessionMember sessionMember = new SessionMember(member);
        HttpSession session = request.getSession(false);
        session.setAttribute("member",sessionMember);
        message(model,"변경되었습니다.","member/info");

        return "message";
    }

    @PostMapping("/info/edit/image")
    public String chageImage(MultipartFile image,@LoginMember SessionMember smember,HttpServletRequest request,Model model) throws IOException {

        Member member = memberService.findByEmail(smember.getEmail());
        if (member.getPassword() == null) {
            message(model,"타 플랫폼 로그인은 개인정보 변경이 불가능합니다.","member/info");
            return "message";
        }
        String path = posterUtilities.storePosterForMember(image);
        member.setPicture(path);
        SessionMember sessionMember = new SessionMember(member);
        HttpSession session = request.getSession(false);
        session.setAttribute("member",sessionMember);
        message(model,"변경되었습니다.","member/info");

        return "message";
    }

    @GetMapping("/delete")
    public String deleteMember(@LoginMember SessionMember smember) {
        Member member = memberService.findByEmail(smember.getEmail());
        List<WishContent> wishes = member.getWishes();
        for (WishContent wish : wishes) {
            wishContentRepository.delete(wish);
        }
        List<WishReview> wishReviews = member.getWishReviews();
        for (WishReview wishReview : wishReviews) {
            wishReviewRepository.delete(wishReview);
        }
        List<Review> posts = member.getPosts();
        for (Review post : posts) {
            reviewService.deleteReview(post.getId());
        }

        memberService.deleteById(member.getId());
        return "redirect:/";
    }

    /**
     * 유틸리티
     */

    private void message(Model model, String message, String url) {
        model.addAttribute("message", message);
        model.addAttribute("url", "http://localhost:8080/" + url);
    }
}
