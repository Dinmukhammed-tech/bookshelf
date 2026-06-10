package com.bookshelf.service;

import com.bookshelf.dto.ReviewRequest;
import com.bookshelf.dto.ReviewResponse;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Review;
import com.bookshelf.entity.User;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.ReviewRepository;
import com.bookshelf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.Authenticator;

@Service
@AllArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewRequest request, String name){
        User user = userRepository.findByUsername(name)
                .orElseThrow(()->new ResourceNotFoundException("User not found:"+name));
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(()->new ResourceNotFoundException("Book not found:"+request.bookId()));

        Review review = new Review();
        review.setRating(request.rating());
        review.setText(request.text());
        review.setUser(user);
        review.setBook(book);

        Review saved = reviewRepository.save(review);
        return ReviewResponse.from(saved);
    }
}
