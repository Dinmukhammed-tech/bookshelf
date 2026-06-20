package com.bookshelf.dto;

import com.bookshelf.entity.ShelfEntry;
import com.bookshelf.entity.ShelfStatus;

public record ShelfResponse(
        Long id,
        Long bookId,
        String bookTitle,
        ShelfStatus status
) {
    public static ShelfResponse from(ShelfEntry entry){
        return new ShelfResponse(
                entry.getId(),
                entry.getBook().getId(),
                entry.getBook().getTitle(),
                entry.getStatus()
        );
    }
}
