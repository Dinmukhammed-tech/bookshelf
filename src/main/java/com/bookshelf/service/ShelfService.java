package com.bookshelf.service;

import com.bookshelf.dto.ShelfRequest;
import com.bookshelf.dto.ShelfResponse;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.ShelfEntry;
import com.bookshelf.entity.User;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.ShelfRepository;
import com.bookshelf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelfService {

    private final ShelfRepository shelfRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ShelfResponse addToShelf(ShelfRequest request, String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("User not found:"+username));
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(()->new ResourceNotFoundException("Book not found:"+request.bookId()));
        ShelfEntry shelfEntry = shelfRepository.findByUserIdAndBookId(user.getId(),book.getId())
                .orElseGet(()-> {
                            ShelfEntry e = new ShelfEntry();
                            e.setUser(user);
                            e.setBook(book);
                            return e;
                        }
                        );
        shelfEntry.setStatus(request.status());

        ShelfEntry saved = shelfRepository.save(shelfEntry);
        return ShelfResponse.from(saved);
    }

    public List<ShelfResponse> getMyShelf(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("User not found:"+username));
        return shelfRepository.findByUserId(user.getId()).stream()
                .map(ShelfResponse :: from)
                .toList();
    }


}
