package com.example.app.util;

import com.example.app.dao.BooksDao;
import com.example.app.models.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for validating Books objects.
 */
@Component
public class BooksValidator implements Validator {

  private final BooksDao booksDao;

  @Autowired
  public BooksValidator(BooksDao booksDao) {
    this.booksDao = booksDao;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Books.class.equals(clazz);
  }

  @Override
  public void validate(Object o, Errors errors) {
    Books books = (Books) o;

    if (booksDao.showFullNameOfBook(books.getName()).isPresent()) {
      errors.rejectValue("name", "", "В базі вже є така книга");
    }
  }
}
