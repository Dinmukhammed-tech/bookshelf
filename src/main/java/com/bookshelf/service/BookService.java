package com.bookshelf.service;

import com.bookshelf.entity.Book;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookRepository;
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

    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book not found:"+id);
        }
        bookRepository.deleteById(id);
    }

}
