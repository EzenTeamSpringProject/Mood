package com.ezen.mood.repository;

import com.ezen.mood.domain.content.poster.Poster;
import com.ezen.mood.util.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosterRepository extends JpaRepository<Poster,Long> {

}
