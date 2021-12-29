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
import com.ezen.mood.util.PosterUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private final ContentCompanyRepository contentCompanyRepository;

    // 컨텐츠 등록
    public void saveContent(ContentFormDto dto) throws IOException {

        Content content = dto.toEntity();
        contentRepository.save(content);

//        카테고리,컴패니,장르,포스터 추가 세팅
        Content content1 = save(dto, content);

    }

    private Content save(ContentFormDto dto, Content content) throws IOException {
        Long categoryId = dto.getCategory();
        Category category = categoryRepository.findById(categoryId).get();
        category.addContent(content);
        content.setCategory(category);

        List<Long> companyIds = dto.getCompanies();
        List<Company> companies = companyIds.stream().map(id -> companyRepository.findById(id).get()).collect(Collectors.toList());
        List<ContentCompany> contentCompanies = new ArrayList<>();
        for (Company company : companies) {
            ContentCompany contentCompany = new ContentCompany();
            contentCompany.putCompany(company);
            contentCompany.putContent(content);
            contentCompanyRepository.save(contentCompany);
            contentCompanies.add(contentCompany);
        }
        content.setCompanies(contentCompanies);

        List<Long> genreIds = dto.getGenres();
        List<Genre> genres = genreIds.stream().map(id -> genreRepository.findById(id).get()).collect(Collectors.toList());
        List<ContentGenre> contentGenres = new ArrayList<>();
        for (Genre genre : genres) {
            ContentGenre contentGenre = new ContentGenre();
            contentGenre.putContent(content);
            contentGenre.putGerne(genre);
            contentGenreRepository.save(contentGenre);
            contentGenres.add(contentGenre);
        }
        content.setGenres(contentGenres);

        Content save = contentRepository.save(content);
        if(!dto.getPoster().isEmpty()){
            Poster poster = posterUtilities.storePoster(dto.getPoster(), save, posterRepository);
            save.setPoster(poster);
        }

        return save;
    }

    // 컨텐츠 삭제
    public void deletePost(Long id) {
        Content content = contentRepository.findById(id).get();
        delete(content);

        contentRepository.delete(content);
    }

    private void delete(Content content) {
        List<ContentGenre> genres = content.getGenres();
        for (ContentGenre genre : genres) {
            contentGenreRepository.delete(genre);
        }
        List<ContentCompany> companies = content.getCompanies();
        for (ContentCompany company : companies) {
            contentCompanyRepository.delete(company);
        }

        Poster poster1 = content.getPoster();
        poster1.deletePhysicFile();
    }

    // 컨텐츠 조회
    @Transactional
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Content findById(Long id) {
        return contentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
    }
    // 컨텐츠 업데이트
    public void updateContent(Long id,ContentFormDto contentFormDto) throws Exception {
        Content content = contentRepository.findById(id).orElse(null);

        List<ContentGenre> genres = content.getGenres();
        for (ContentGenre genre : genres) {
            contentGenreRepository.delete(genre);
        }
        List<ContentCompany> companies = content.getCompanies();
        for (ContentCompany company : companies) {
            contentCompanyRepository.delete(company);
        }
        if(!contentFormDto.getPoster().isEmpty()){
            Poster poster1 = content.getPoster();
            poster1.deletePhysicFile();
        }


        save(contentFormDto, content);
    }

}
