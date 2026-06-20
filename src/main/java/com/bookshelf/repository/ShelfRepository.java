package com.bookshelf.repository;

import com.bookshelf.entity.ShelfEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<ShelfEntry,Long> {
    Optional<ShelfEntry> findByUserIdAndBookId(Long userId, Long bookId);
    List<ShelfEntry> findByUserId(Long userId);
}
