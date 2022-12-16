package com.example.app.util;

import com.example.app.models.Books;
import com.example.app.services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for validating Books objects.
 */
@Component
public class BooksValidator implements Validator {

  private final BooksServices booksServices;

  @Autowired
  public BooksValidator(BooksServices booksServices) {
    this.booksServices = booksServices;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Books.class.equals(clazz);
  }

  @Override
  public void validate(Object o, Errors errors) {
    Books books = (Books) o;

    if (booksServices.findByName(books.getName()) != null) {
      errors.rejectValue("name", "", "В базі вже є така книга");
    }
  }
}
