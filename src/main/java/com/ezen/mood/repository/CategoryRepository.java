package com.ezen.mood.repository;

import com.ezen.mood.domain.content.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
