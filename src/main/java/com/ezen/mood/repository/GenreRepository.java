package com.ezen.mood.repository;

import com.ezen.mood.domain.content.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {

}
