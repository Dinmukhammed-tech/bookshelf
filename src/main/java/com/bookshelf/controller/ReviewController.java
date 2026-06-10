package com.bookshelf.controller;

import com.bookshelf.dto.ReviewRequest;
import com.bookshelf.dto.ReviewResponse;
import com.bookshelf.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;

@Controller
@AllArgsConstructor
@RequestMapping("api/v1/books")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("create_review")
    public ReviewResponse createReview(@Valid @RequestBody ReviewRequest reviewRequest, Authentication authentication){
        String name = authentication.getName();
        return reviewService.createReview(reviewRequest,name);
    }
}
