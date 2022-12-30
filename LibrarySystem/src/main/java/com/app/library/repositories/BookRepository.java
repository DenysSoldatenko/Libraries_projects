package com.app.library.repositories;

import com.app.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Book entities.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
