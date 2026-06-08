package com.bookshelf.controller;

import com.bookshelf.entity.Book;
import com.bookshelf.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book create(@Valid @RequestBody Book book){
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id){
        bookService.deleteBook(id);
    }

}
