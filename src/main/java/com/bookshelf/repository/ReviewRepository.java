package com.bookshelf.repository;

import com.bookshelf.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBookId(Long bookId);

    @Query("select avg(r.rating) from Review r where r.book.id = :bookId")
    Double findAverageRatingByBookId(@Param("bookId") Long bookId);

}
