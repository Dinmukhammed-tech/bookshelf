package com.bookshelf.service;

import com.bookshelf.entity.Book;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found:"+id));
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

}
