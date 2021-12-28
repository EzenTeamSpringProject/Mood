package com.ezen.mood.repository;

import com.ezen.mood.domain.review.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    List<Posts> findAllByOrderByIdDesc();
}
