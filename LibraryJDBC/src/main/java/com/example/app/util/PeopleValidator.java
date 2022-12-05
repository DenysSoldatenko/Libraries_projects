package com.example.app.util;

import com.example.app.dao.PeopleDao;
import com.example.app.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for validating People objects.
 */
@Component
public class PeopleValidator implements Validator {

  private final PeopleDao peopleDao;

  @Autowired
  public PeopleValidator(PeopleDao peopleDao) {
    this.peopleDao = peopleDao;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return People.class.equals(clazz);
  }

  @Override
  public void validate(Object o, Errors errors) {
    People people = (People) o;

    if (peopleDao.showFullName(people.getFullName()).isPresent()) {
      errors.rejectValue("fullName", "", "В базі вже є такий користувач");
    }
  }
}
