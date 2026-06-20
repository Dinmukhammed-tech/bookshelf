package com.bookshelf.dto;

import com.bookshelf.entity.ShelfStatus;
import jakarta.validation.constraints.NotNull;

public record ShelfRequest(
        @NotNull Long bookId,
        @NotNull ShelfStatus status
) {
}
