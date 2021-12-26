package com.ezen.mood.controller;


import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.domain.content.enums.Rating;
import com.ezen.mood.domain.content.genre.Genre;
import com.ezen.mood.dto.ContentFormDto;
import com.ezen.mood.repository.CategoryRepository;
import com.ezen.mood.repository.CompanyRepository;
import com.ezen.mood.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/contents")
@Controller
public class ContentController {

    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final GenreRepository genreRepository;

    /**
     * 컨텐츠 CRUD
     */
//    관리자용
    @GetMapping("/post")
    public String postForm(@ModelAttribute("dto") ContentFormDto dto, Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("cates",categories);
        model.addAttribute("rates", Rating.values());
        model.addAttribute("comps",companies);
        model.addAttribute("gens",genres);
        return "content/contentForm";
    }

//    @PostMapping("/post")
//    public String post(@ModelAttribute("dto") ContentFormDto dto) {
//
//    }





}
