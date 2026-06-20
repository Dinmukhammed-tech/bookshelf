package com.bookshelf.controller;

import com.bookshelf.dto.ReviewRequest;
import com.bookshelf.dto.ReviewResponse;
import com.bookshelf.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewResponse createReview(@Valid @RequestBody ReviewRequest reviewRequest, Authentication authentication){
        String name = authentication.getName();
        return reviewService.createReview(reviewRequest,name);
    }

    @GetMapping
    public List<ReviewResponse> getByBook(@RequestParam Long bookId){
        return reviewService.getReviewByBookId(bookId);
    }
}
