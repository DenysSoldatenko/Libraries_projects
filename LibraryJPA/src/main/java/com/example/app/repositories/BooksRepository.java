package com.example.app.repositories;

import com.example.app.models.Books;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing book entities.
 */
@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
  Books findByName(String string);

  List<Books> findAllByNameStartingWith(String string);
}
