package com.example.app.services;

import com.example.app.models.Books;
import com.example.app.models.People;
import com.example.app.repositories.BooksRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing book-related operations.
 */
@Service
@Transactional(readOnly = true)
public class BooksServices {

  private final BooksRepository booksRepository;

  @Autowired
  public BooksServices(BooksRepository booksRepository) {
    this.booksRepository = booksRepository;
  }

  /**
  * Retrieves all books.
  *
  * @param sortByYear true if books should be sorted by year, false otherwise
  * @return a list of all books, optionally sorted by year
  */
  public List<Books> findAll(boolean sortByYear) {
    if (sortByYear) {
      return booksRepository.findAll(Sort.by("year"));
    } else {
      return booksRepository.findAll();
    }
  }

  /**
  * Retrieves books with pagination.
  *
  * @param page         the page number
  * @param booksPerPage the number of books per page
  * @param sortByYear   true if books should be sorted by year, false otherwise
  * @return a list of books for the specified page and books per page, optionally sorted by year
  */
  public List<Books> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
    if (sortByYear) {
      return booksRepository.findAll(PageRequest.of(page, booksPerPage,
      Sort.by("year"))).getContent();
    } else {
      return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
  }

  public Books findOne(int id) {
    Optional<Books> foundPerson = booksRepository.findById(id);
    return foundPerson.orElse(null);
  }

  @Transactional
  public void save(Books books) {
    booksRepository.save(books);
  }

  public People getBookOwner(int id) {
    return booksRepository.findById(id).map(Books::getPeople).orElse(null);
  }

  @Transactional
  public void update(int id, Books updatedBook) {
    updatedBook.setId(id);
    booksRepository.save(updatedBook);
  }

  /**
  * Assigns a book to a person.
  *
  * @param id     the ID of the book to assign
  * @param people the person to assign the book to
  */
  @Transactional
  public void assignBook(int id, People people) {
    booksRepository.findById(id).ifPresent(
        books -> {
        books.setPeople(people);
        books.setTakenAt(new Date());
      });
  }

  /**
  * Releases a book from a person.
  *
  * @param id the ID of the book to release
  */
  @Transactional
  public void releaseBook(int id) {
    booksRepository.findById(id).ifPresent(
        books -> {
        books.setPeople(null);
        books.setTakenAt(null);
      });
  }

  @Transactional
  public void delete(int id) {
    booksRepository.deleteById(id);
  }

  public Books findByName(String string) {
    return booksRepository.findByName(string);
  }

  public List<Books> findAllByNameStartingWith(String string) {
    return booksRepository.findAllByNameStartingWith(string);
  }
}
