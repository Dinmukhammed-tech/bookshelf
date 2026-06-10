package com.bookshelf.service;

import com.bookshelf.dto.BookResponse;
import com.bookshelf.entity.Book;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> getAllBooks(){
        List<Book> list = bookRepository.findAll();
//        List<BookResponse> res = new ArrayList<>();
//        for (Book book : list){
//            res.add(BookResponse.from(book));
//        }
        return list.stream()
                .map(BookResponse :: from)
                .toList();
    }

    public BookResponse getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found:"+id));
        return BookResponse.from(book);
    }

    public BookResponse createBook(Book book){

        Book saved = bookRepository.save(book);
        return BookResponse.from(saved);
    }

    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book not found:"+id);
        }
        bookRepository.deleteById(id);
    }

}
