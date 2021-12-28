package com.ezen.mood.repository;

import com.ezen.mood.domain.content.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
