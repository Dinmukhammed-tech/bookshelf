package com.bookshelf.controller;

import com.bookshelf.dto.BookResponse;
import com.bookshelf.entity.Book;
import com.bookshelf.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;



    @GetMapping
    public Page<BookResponse> getAllBooks(Pageable pageable){
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookResponse create(@Valid @RequestBody Book book){
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<BookResponse> search(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title
    ){
        return bookService.searchBooks(genre, author, title);
    }

}
