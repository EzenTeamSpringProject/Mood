package com.ezen.mood.repository;

import com.ezen.mood.domain.content.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

     Optional<Category> findByKrname(String krname);
}
