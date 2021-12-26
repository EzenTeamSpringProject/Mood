package com.ezen.mood.controller.admin;

import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.domain.content.genre.Genre;
import com.ezen.mood.dto.GccDto;
import com.ezen.mood.service.admin.CompanyService;
import com.ezen.mood.service.admin.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final GenreService genreService;
    private final CompanyService companyService;

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
        model.addAttribute("gccUrl","genres");
        return "admin/content/gccList";
    }

    @GetMapping("/genres/add")
    public String addGenreForm(@ModelAttribute("dto") GccDto dto) {
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
        model.addAttribute("gccUrl","companies");
        return "admin/content/gccList";
    }

    @GetMapping("/companies/add")
    public String addCompanyForm(@ModelAttribute("dto") GccDto dto) {
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
}
