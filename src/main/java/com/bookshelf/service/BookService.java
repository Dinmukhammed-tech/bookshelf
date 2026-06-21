package com.bookshelf.service;

import com.bookshelf.dto.BookResponse;
import com.bookshelf.entity.Book;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.ReviewRepository;
import com.bookshelf.spec.BookSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {


    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;


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
        Double avg = reviewRepository.findAverageRatingByBookId(id);
        return BookResponse.from(book,avg);
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

}
