package com.app.library.services;


import com.app.library.models.Author;
import com.app.library.repositories.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Author-related operations.
 */
@Service
public class AuthorService {
  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> findAllAuthors() {
    return authorRepository.findAll();
  }

  public Author findAuthorsById(int id) {
    return authorRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Author not found"));
  }

  public void saveAuthor(Author author) {
    authorRepository.save(author);
  }

  public void deleteAuthor(int id) {
    authorRepository.deleteById(id);
  }

  public void updateAuthor(int id, Author author) {
    author.setId(id);
    authorRepository.save(author);
  }
}
