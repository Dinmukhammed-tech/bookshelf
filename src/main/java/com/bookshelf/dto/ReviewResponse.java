package com.bookshelf.dto;

import com.bookshelf.entity.Review;

public record ReviewResponse(
        Long id,
        Integer rating,
        String text,
        String username,
        Long bookId
) {
    public static ReviewResponse from(Review review){
        return new ReviewResponse(review.getId(), review.getRating(), review.getText(), review.getUser().getUsername(), review.getBook().getId());
    }
}
