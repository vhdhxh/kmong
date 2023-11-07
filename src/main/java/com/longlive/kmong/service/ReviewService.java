package com.longlive.kmong.service;

import com.longlive.kmong.DAO.Review;
import com.longlive.kmong.DTO.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final Review review;

    public void insertReview(ReviewDto reviewDto) {
        review.insertReview(reviewDto);
    }
}
