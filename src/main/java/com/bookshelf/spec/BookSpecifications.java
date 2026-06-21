package com.bookshelf.spec;

import com.bookshelf.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> hasGenre(String genre){
        return (root, query, cb) ->
            genre == null ? null : cb.equal(root.get("genre"),genre);
    }

    public static Specification<Book> hasAuthor(String author){
        return (root, query, cb) ->
                author == null ? null : cb.like(root.get("author"),"%"+author+"%");
    }

    public static Specification<Book> titleContains(String title){
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("author")),"%"+title.toLowerCase()+"%");
    }


}
