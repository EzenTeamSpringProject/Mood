package com.ezen.mood.repository;

import com.ezen.mood.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByMember_Email(String email);
}
