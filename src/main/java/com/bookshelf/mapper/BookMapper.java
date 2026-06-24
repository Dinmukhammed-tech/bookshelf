package com.bookshelf.mapper;

import com.bookshelf.dto.BookResponse;
import com.bookshelf.entity.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toResponse(Book book);
}
