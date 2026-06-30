package com.bookshelf.dto;

import com.bookshelf.entity.Book;

import java.io.Serializable;

public record BookResponse(
        Long id,
        String title,
        String author,
        String description,
        String genre,
        Double averageRating
) implements Serializable {
    public static BookResponse from(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getGenre(), null);
    }
    public static BookResponse from(Book book, Double averageRating){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getGenre(), averageRating);
    }

}
