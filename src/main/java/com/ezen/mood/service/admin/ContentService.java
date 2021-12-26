package com.ezen.mood.service.admin;

import com.ezen.mood.domain.content.Content;

import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.domain.content.company.ContentCompany;
import com.ezen.mood.domain.content.genre.ContentGenre;
import com.ezen.mood.domain.content.genre.Genre;
import com.ezen.mood.domain.content.poster.Poster;
import com.ezen.mood.dto.ContentFormDto;
import com.ezen.mood.repository.*;
import com.ezen.mood.util.Files;
import com.ezen.mood.util.PosterUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final GenreRepository genreRepository;
    private final ContentGenreRepository contentGenreRepository;
    private final PosterRepository posterRepository;
    private final PosterUtilities posterUtilities;

    // 컨텐츠 등록
    public void saveContent(ContentFormDto dto) throws IOException {
        Content content = dto.toEntity();
//        카테고리,컴패니,장르,포스터 추가 세팅
        Long categoryId = dto.getCategory();
        Category category = categoryRepository.findById(categoryId).get();
        category.addContent(content);

        List<Long> companyIds = dto.getCompanies();
        List<Company> companies = companyIds.stream().map(id -> companyRepository.findById(id).get()).collect(Collectors.toList());
        List<ContentCompany> contentCompanies = new ArrayList<>();
        for (Company company : companies) {
            ContentCompany contentCompany = new ContentCompany();
            contentCompany.putCompany(company);
            contentCompany.putContent(content);
            contentCompanies.add(contentCompany);
        }

        List<Long> genreIds = dto.getGenres();
        List<Genre> genres = genreIds.stream().map(id -> genreRepository.findById(id).get()).collect(Collectors.toList());
        List<ContentGenre> contentGenres = new ArrayList<>();
        for (Genre genre : genres) {
            ContentGenre contentGenre = new ContentGenre();
            contentGenre.putContent(content);
            contentGenre.putGerne(genre);
            contentGenres.add(contentGenre);
        }

        List<Poster> posters = posterUtilities.storePosters(dto.getPosters(),content);

        contentRepository.save(content);
    }
    // 컨텐츠 삭제

    // 컨텐츠 조회

    // 컨텐츠

}
