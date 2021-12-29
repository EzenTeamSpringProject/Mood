package com.ezen.mood.service.member;


import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.content.poster.Poster;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.review.Review;
import com.ezen.mood.dto.ReviewFormDto;
import com.ezen.mood.repository.PosterRepository;
import com.ezen.mood.repository.ReviewRepository;
import com.ezen.mood.service.common.MemberService;
import com.ezen.mood.util.PosterUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final MemberService memberService;
    private final ReviewRepository reviewRepository;
    private final PosterRepository posterRepository;
    private final PosterUtilities posterUtilities;

    public void saveReview(ReviewFormDto dto, SessionMember smember) throws IOException {
//        리뷰폼으로 받은 데이터를 엔티티로 만들어서 일단 저장
        Review review = dto.toEntity();
        reviewRepository.save(review);
//        세션멤버를 멤버엔티티로.
        Member member = memberService.findByEmail(smember.getEmail());

//        멤버 붙이기
        review.setMember(member);

//        이미지 붙이기기
        Poster poster = posterUtilities.storePosterForReview(dto.getImage(), review, posterRepository);
        review.setPoster(poster);
    }

    public void deleteReview(Long id) {
//        멤버검증은 컨트롤러에서
        Review review = reviewRepository.findById(id).orElse(null);
        if(review.getPoster()!=null)
        review.getPoster().deletePhysicFile();
        reviewRepository.delete(review);
    }

    public List<Review>  findAllOfMyReview(SessionMember member) {
        return reviewRepository.findAllByMember_Email(member.getEmail());
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public void updateReview(Long id,ReviewFormDto reviewFormDto) throws IOException {
        Review review = reviewRepository.findById(id).orElse(null);
        review.setTitle(reviewFormDto.getTitle());
        review.setContent(reviewFormDto.getContent());
        if(!reviewFormDto.getImage().isEmpty()){
            review.getPoster().deletePhysicFile();
            Poster poster = posterUtilities.storePosterForReview(reviewFormDto.getImage(), review, posterRepository);
            review.setPoster(poster);
        }

    }
}
