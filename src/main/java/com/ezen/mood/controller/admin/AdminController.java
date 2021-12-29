package com.ezen.mood.controller.admin;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.domain.content.enums.Rating;
import com.ezen.mood.domain.content.genre.Genre;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.dto.ContentFormDto;
import com.ezen.mood.dto.ContentViewDto;
import com.ezen.mood.dto.GccDto;
import com.ezen.mood.repository.*;
import com.ezen.mood.service.admin.CategoryService;
import com.ezen.mood.service.admin.CompanyService;
import com.ezen.mood.service.admin.ContentService;
import com.ezen.mood.service.admin.GenreService;
import com.ezen.mood.service.common.MemberService;
import com.ezen.mood.util.PosterUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final GenreService genreService;
    private final CompanyService companyService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final GenreRepository genreRepository;
    private final ContentService contentService;
    private final PosterUtilities posterUtilities;
    private final MemberService memberService;

    //    관리자 페이지 - 메인
    @GetMapping
    public String adminPage() {
        return "/admin/adminIndex";
    }

    /**
     * 장르 관련 어드민
     * 관리자 페이지 - 메인 - 장르
     */

    @GetMapping("/genres")
    public String genreList(Model model) {
        List<Genre> genres = genreService.loadGenreList();
        model.addAttribute("gccs", genres);
        model.addAttribute("gccUrl", "genres");
        return "admin/content/gccList";
    }

    @GetMapping("/genres/add")
    public String addGenreForm(@ModelAttribute("dto") GccDto dto, Model model) {
        model.addAttribute("type", "Genre");
        return "admin/content/addForm";
    }

    @PostMapping("/genres/add")
    public String addGenre(@ModelAttribute("dto") GccDto dto) {
        genreService.saveGenre(dto);

        return "redirect:/admin/genres";
    }

    @PostMapping("/genres/delete")
    public String deleteGenre(@RequestParam(required = false) List<Long> deleteId) {
        if (deleteId == null) {
            return "redirect:/admin/genres";
        }
        for (Long id : deleteId) {
            genreService.removeGenre(id);
        }
        return "redirect:/admin/genres";
    }

    /**
     * 관리자 페이지 - 메인 - 제공업체
     */
    @GetMapping("/companies")
    public String companyList(Model model) {
        List<Company> companies = companyService.loadCompanyList();
        model.addAttribute("gccs", companies);
        model.addAttribute("gccUrl", "companies");
        return "admin/content/gccList";
    }

    @GetMapping("/companies/add")
    public String addCompanyForm(@ModelAttribute("dto") GccDto dto, Model model) {
        model.addAttribute("type", "Provider");

        return "admin/content/addForm";
    }

    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute("dto") GccDto dto) {
        companyService.saveCompany(dto);

        return "redirect:/admin/companies";
    }

    @PostMapping("/companies/delete")
    public String deleteCompany(@RequestParam(required = false) List<Long> deleteId) {
        if (deleteId == null) {
            return "redirect:/admin/companies";
        }

        for (Long id : deleteId) {
            companyService.removeCompany(id);
        }
        return "redirect:/admin/companies";
    }

    /**
     * 관리자 페이지 - 메인 - 카테고리
     */
    @GetMapping("/categories")
    public String categoryList(Model model) {
        List<Category> categories = categoryService.loadCategoryList();
        model.addAttribute("gccs", categories);
        model.addAttribute("gccUrl", "categories");
        return "admin/content/gccList";
    }

    @GetMapping("/categories/add")
    public String addCategoryForm(@ModelAttribute("dto") GccDto dto, Model model) {
        model.addAttribute("type", "Category");
        return "admin/content/addForm";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute("dto") GccDto dto) {
        categoryService.saveCategory(dto);

        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/delete")
    public String deleteCategory(@RequestParam(required = false) List<Long> deleteId) {
        if (deleteId == null) {
            return "redirect:/admin/categories";
        }
        for (Long id : deleteId) {
            categoryService.removeCategory(id);
        }
        return "redirect:/admin/categories";
    }

    /**
     * 컨텐츠
     */

    @GetMapping("/contents")
    public String list(Model model) {
        List<Content> contents = contentService.findAll();

        model.addAttribute("contents", contents);
        return "admin/content/contentList";
    }

    @GetMapping("/contents/{id}")
    public String findCotent(@PathVariable Long id, Model model) {
        Content entity = contentService.findById(id);
        ContentViewDto content = entity.toViewDto();
        model.addAttribute("content", content);
        return "admin/content/contentView";
    }

    @GetMapping("/contents/{id}/delete")
    public String deleteCotent(@PathVariable Long id, Model model) {
        contentService.deletePost(id);
        message(model, "컨텐츠가 삭제되었습니다.", "admin/contents");
        return "message";
    }

    @GetMapping("/contents/{id}/edit")
    public String editContentForm(@PathVariable Long id, Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("cates", categories);
        model.addAttribute("rates", Rating.values());
        model.addAttribute("comps", companies);
        model.addAttribute("gens", genres);

        Content entity = contentService.findById(id);
        ContentFormDto contentFormDto = entity.toFormDto();
        model.addAttribute("dto",contentFormDto);
        return "admin/content/contentForm";
    }

    @PostMapping("/contents/{id}/edit")
    public String editContent(@PathVariable Long id,@ModelAttribute("dto") ContentFormDto dto) throws Exception {
        contentService.updateContent(id,dto);
        return "redirect:/admin/contents/"+id;
    }

    /**
     * 컨텐츠 CRUD
     */
//    관리자용
    @GetMapping("/contents/post")
    public String postForm(@ModelAttribute("dto") ContentFormDto dto, Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("cates", categories);
        model.addAttribute("rates", Rating.values());
        model.addAttribute("comps", companies);
        model.addAttribute("gens", genres);
        return "admin/content/contentForm";
    }

    @PostMapping("/contents/post")
    public String post(@ModelAttribute("dto") ContentFormDto dto) throws IOException {
        contentService.saveContent(dto);
        return "redirect:/admin/contents";
    }


    @ResponseBody
    @GetMapping("/contents/files/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + posterUtilities.getFullPath(filename));
    }


    /**
     * 멤버 관리
     */
    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "admin/member/memberList";
    }

    @PostMapping("/members/delete")
    public String deleteMember(@RequestParam(required = false) List<Long> ids) {
        for (Long id : ids) {
            memberService.deleteById(id);
        }
        return "redirect:/admin/members";
    }


    /**
     * 유틸리티
     */

    private void message(Model model, String message, String url) {
        model.addAttribute("message", message);
        model.addAttribute("url", "http://localhost:8080/" + url);
    }

}
