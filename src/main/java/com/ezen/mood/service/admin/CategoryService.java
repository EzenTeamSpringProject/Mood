package com.ezen.mood.service.admin;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.dto.GccDto;
import com.ezen.mood.repository.CategoryRepository;
import com.ezen.mood.repository.CompanyRepository;
import com.ezen.mood.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ContentRepository contentRepository;

    public Category saveCategory(GccDto gccDto) {
        Category newEntity = GccDto.toCategoryEntity(gccDto);
        categoryRepository.save(newEntity);
        return categoryRepository.findById(newEntity.getId()).orElse(null);
    }

    public List<Category> loadCategoryList() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    public Category loadCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category findByKrname(String krname) {
        Category category = categoryRepository.findByKrname(krname).orElse(null);
        return category;

    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        List<Content> contentList = category.getContentList();
        for (Content content : contentList) {
            if (content != null) {
                content.setCategory(null);
            }
        }

        categoryRepository.deleteById(id);
    }

    // 저장메소드랑 수정메소드랑 if 분기점을 통해서 결합할 수도 있긴 하겠다.
    public void modifyCategory(GccDto gccDto) {
        Category newEntity = GccDto.toCategoryEntity(gccDto);

        Optional<Category> original = categoryRepository.findById(newEntity.getId());

        original.ifPresent(category -> {
            category.setEngname(newEntity.getEngname());
            category.setKrname(newEntity.getKrname());
        });
    }
}
