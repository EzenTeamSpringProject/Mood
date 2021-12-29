package com.ezen.mood.repository;

import com.ezen.mood.domain.content.poster.Poster;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PosterRepository extends JpaRepository<Poster,Long> {

}
