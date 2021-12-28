package com.ezen.mood.repository;

import com.ezen.mood.util.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files,Long> {

    List<Files> findByPostId(Long id);
}
