package com.ezen.mood.repository;

import com.ezen.mood.domain.content.company.ContentCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentOttRepository extends JpaRepository<ContentCompany,Long> {
}
