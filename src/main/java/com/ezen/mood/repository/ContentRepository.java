package com.ezen.mood.repository;

import com.ezen.mood.domain.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContentRepository extends JpaRepository<Content,Long> {
}
