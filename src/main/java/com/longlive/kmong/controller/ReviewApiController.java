package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.ReviewDto;
import com.longlive.kmong.service.OrderService;
import com.longlive.kmong.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewApiController {
    private final ReviewService reviewService;
    private final OrderService orderService;

//    리뷰쓰기
    @PostMapping("/{orderId}")
    public void writeReview(ReviewDto reviewDto, @PathVariable Long orderId) {
        reviewService.insertReview(reviewDto);
        orderService.updateStatus2(orderId);

    }
}
