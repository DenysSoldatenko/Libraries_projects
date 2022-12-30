package com.app.library.repositories;

import com.app.library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Author entities.
 */
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
