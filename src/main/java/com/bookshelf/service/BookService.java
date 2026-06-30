package com.bookshelf.service;

import com.bookshelf.dto.BookResponse;
import com.bookshelf.entity.Book;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.mapper.BookMapper;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.ReviewRepository;
import com.bookshelf.spec.BookSpecifications;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {


    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final BookMapper bookMapper;


    

    public Page<BookResponse> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable).map(BookResponse :: from);
    }

    @Cacheable(value = "books", key = "#id")
    public BookResponse getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found:"+id));
        Double avg = reviewRepository.findAverageRatingByBookId(id);
        return BookResponse.from(book,avg);
    }



    public BookResponse createBook(Book book){

        Book saved = bookRepository.save(book);
        return BookResponse.from(saved);
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book not found:"+id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookResponse> searchBooks(String genre, String author, String title){
        Specification<Book> spec = Specification.allOf(
                BookSpecifications.hasGenre(genre),
                BookSpecifications.hasAuthor(author),
                BookSpecifications.titleContains(title)
        );
        return bookRepository.findAll(spec).stream()
                .map(BookResponse :: from)
                .toList();
    }

    @CacheEvict(value = "books", key = "#id")
    public BookResponse updateBook(Long id, Book updated){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book not found:"+id));
        book.setAuthor(updated.getAuthor());
        book.setGenre(updated.getGenre());
        book.setTitle(updated.getTitle());
        book.setDescription(updated.getDescription());
        Book saved = bookRepository.save(book);
        return BookResponse.from(saved);
    }

}
