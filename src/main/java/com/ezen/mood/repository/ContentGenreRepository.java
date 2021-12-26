package com.ezen.mood.repository;

import com.ezen.mood.domain.content.genre.ContentGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentGenreRepository extends JpaRepository<ContentGenre,Long> {
}
