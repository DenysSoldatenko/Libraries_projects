package com.app.library.services;

import com.app.library.models.Book;
import com.app.library.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Book-related operations.
 */
@Service
public class BookService {
  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Book findBooksById(int id) {
    return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
  }

  public void saveBook(Book book) {
    bookRepository.save(book);
  }

  public void deleteBook(int id) {
    bookRepository.deleteById(id);
  }

  public void updateBook(int id, Book book) {
    book.setId(id);
    bookRepository.save(book);
  }
}
