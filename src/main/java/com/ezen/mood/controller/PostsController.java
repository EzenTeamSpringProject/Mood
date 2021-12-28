package com.ezen.mood.controller;

import com.ezen.mood.config.auth.LoginMember;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.review.Posts;
import com.ezen.mood.dto.PostFormDto;
import com.ezen.mood.service.PostsService;
import com.ezen.mood.util.FileUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostsController {

    private final PostsService postsService;
    private final FileUtilities fileUtilities;

    //    Create
    @GetMapping("/member/reviews")
    public String postWriteForm(@ModelAttribute("post") Posts post) {

        return "post/postWriteForm";
    }


    @PostMapping("/write")
    public String savePosts(PostFormDto postFormDto, Model model, @LoginMember SessionMember sessionMember) throws Exception {
        Posts post = postsService.save(postFormDto, sessionMember.getEmail());

        message(model, "글 작성이 완료되었습니다", "posts/list");
        return "message";
    }


    //    retrieve
    @GetMapping("/list")
    public String postList(Model model) {
        List<Posts> posts = postsService.findAll();
        model.addAttribute("posts", posts);
        return "post/postList";
    }

    @GetMapping("/list/{id}")
    public String postList(@PathVariable Long id, Model model) {
        Posts post = postsService.findById(id);
        model.addAttribute("post", post);
        return "post/postView";
    }

    @GetMapping("/list/{id}/delete")
    public String deletePost(@PathVariable Long id, Model model) {
        postsService.deletePost(id);

        message(model, "글이 삭제되었습니다.", "posts/list");
        return "message";
    }

    @GetMapping("/list/{id}/modify")
    public String modifyPost(@PathVariable Long id, Model model) {
        Posts post = postsService.findById(id);
        model.addAttribute("post", post);
        return "post/postModifyForm";
    }

    @PostMapping("/list/{id}/modify")
    public String modifyPost(@PathVariable Long id, @ModelAttribute PostFormDto postFormDto, Model model, @LoginMember SessionMember member) throws Exception {
        postsService.updatePost(id, member.getEmail(), postFormDto);

        message(model, "글이 수정되었습니다.", "posts/list");
        return "message";
    }


    @ResponseBody
    @GetMapping("/files/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileUtilities.getFullPath(filename));
    }


    // alert 메세지용 메소드
    private void message(Model model, String message, String url) {
        model.addAttribute("message", message);
        model.addAttribute("url", "http://localhost:8080/" + url);
    }


}
