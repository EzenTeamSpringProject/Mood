package com.ezen.mood.repository;

import com.ezen.mood.domain.review.WishReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishReviewRepository extends JpaRepository<WishReview,Long> {
}
