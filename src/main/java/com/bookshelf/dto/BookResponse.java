package com.bookshelf.dto;

import com.bookshelf.entity.Book;

public record BookResponse(
        Long id,
        String title,
        String author,
        String description,
        String genre
) {
    public static BookResponse from(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getGenre());
    }
}
