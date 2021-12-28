package com.ezen.mood.controller.main;

import com.ezen.mood.config.auth.LoginMember;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.member.WishContent;
import com.ezen.mood.dto.ContentViewDto;
import com.ezen.mood.repository.WishContentRepository;
import com.ezen.mood.service.admin.CategoryService;
import com.ezen.mood.service.admin.ContentService;
import com.ezen.mood.service.common.MemberService;
import com.ezen.mood.util.PosterUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    private final ContentService contentService;
    private final PosterUtilities posterUtilities;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final WishContentRepository wishContentRepository;

    /**
     *  페이지 인덱스(HOME)
     */
    @GetMapping("/")
    public String index(Model model) {
        List<Content> contents = contentService.findAll();
        model.addAttribute("contents", contents);

        return "index";
    }

    /**
     * 영화
     */
    @GetMapping("/movie")
    public String movieList(Model model) {
        Category category = categoryService.findByKrname("영화");
        List<Content> contents = category.getContentList();

        model.addAttribute("contents",contents);

        model.addAttribute("category","movie");
        return "main/genreBoard";
    }
    /**
     * 드라마
     */
    @GetMapping("/drama")
    public String dramaList(Model model) {
        Category category = categoryService.findByKrname("드라마");
        List<Content> contents = category.getContentList();

        model.addAttribute("contents",contents);

        model.addAttribute("category","drama");
        return "main/genreBoard";
    }


    /**
     * 애니메이션
     */
    @GetMapping("/animation")
    public String animeList(Model model) {
        Category category = categoryService.findByKrname("애니메이션");
        List<Content> contents = category.getContentList();

        model.addAttribute("contents",contents);

        model.addAttribute("category","anime");
        return "main/genreBoard";
    }

    /**
     *  컨텐츠 뷰 페이지
     */
    @GetMapping("/contents/{id}")
    public String findContent(@PathVariable Long id, Model model,@LoginMember SessionMember member) {
        Content entity = contentService.findById(id);
        ContentViewDto content = entity.toViewDto();
//        좋아요 눌렀는지 처리.
        if(member!=null) {
            Member mEntity = memberService.findByEmail(member.getEmail());
            List<WishContent> wishes = mEntity.getWishes();
            for (WishContent wish : wishes) {
                if (wish.getContent().equals(entity)) {
                    model.addAttribute("isLike", true);
                    break;
                }
                model.addAttribute("isLike", false);
            }
        }
        model.addAttribute("content", content);
        return "main/contentView";
    }

    @PostMapping("/contents/like")
    public String likeContents(@RequestParam Long id,@RequestParam String uri,@LoginMember SessionMember smember) {

        Content content = contentService.findById(id);
        log.info("content : {}",content);
        Member member = memberService.findByEmail(smember.getEmail());
        log.info("member : {}",member);

        WishContent wc = WishContent.builder()
                .content(content)
                .member(member)
                .build();

        List<WishContent> wishes = member.getWishes();
        log.info("wishes : {}",content);
        if(wishes.size()==0){
            wishContentRepository.save(wc);

        }else {
            boolean isExist =true;
            for (WishContent wish : wishes) {
                if (wish.getContent().equals(content)) {
                    wishContentRepository.delete(wish);
                    isExist=true;
                    break;
                }
                isExist=false;
            }
            if (!isExist) {
                wishContentRepository.save(wc);
            }
        }

        return "redirect:"+uri;
    }
    /**
     *  로컬에 저장된 이미지 불러오기
     */
    @ResponseBody
    @GetMapping("/contents/files/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + posterUtilities.getFullPath(filename));
    }
}
