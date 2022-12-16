package com.example.app.util;

import com.example.app.models.People;
import com.example.app.services.PeopleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for validating People objects.
 */
@Component
public class PeopleValidator implements Validator {

  private final PeopleServices peopleServices;

  @Autowired
  public PeopleValidator(PeopleServices peopleServices) {
    this.peopleServices = peopleServices;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return People.class.equals(clazz);
  }

  @Override
  public void validate(Object o, Errors errors) {
    People people = (People) o;

    if (peopleServices.getPeopleByFullName(people.getFullName()) != null) {
      errors.rejectValue("fullName", "", "В базі вже є такий користувач");
    }
  }
}
