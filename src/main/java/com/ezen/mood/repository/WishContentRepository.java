package com.ezen.mood.repository;

import com.ezen.mood.domain.member.WishContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishContentRepository extends JpaRepository<WishContent,Long> {
}
