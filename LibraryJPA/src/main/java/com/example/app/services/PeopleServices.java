package com.example.app.services;

import com.example.app.models.Books;
import com.example.app.models.People;
import com.example.app.repositories.PeopleRepository;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing people-related operations.
 */
@Service
@Transactional(readOnly = true)
public class PeopleServices {
  private final PeopleRepository peopleRepository;

  @Autowired
  public PeopleServices(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  public List<People> findAll() {
    return peopleRepository.findAll();
  }

  public People findOne(int id) {
    Optional<People> foundPerson = peopleRepository.findById(id);
    return foundPerson.orElse(null);
  }

  @Transactional
  public void save(People person) {
    //person.setCreated_at(new Date());
    peopleRepository.save(person);
  }

  @Transactional
  public void update(int id, People updatedPerson) {
    updatedPerson.setId(id);
    peopleRepository.save(updatedPerson);
  }

  @Transactional
  public void delete(int id) {
    peopleRepository.deleteById(id);
  }

  public People getPeopleByFullName(String string) {
    return peopleRepository.getPeopleByFullName(string);
  }

  /**
  * Retrieves a list of books for a person with delay information.
  *
  * @param id the ID of the person
  * @return a list of books for the person with delay information
  */
  @Transactional
  public List<Books> forDelay(int id) {

    Optional<People> people = peopleRepository.findById(id);

    if (people.isPresent()) {
      Hibernate.initialize(people.get().getBooksList());

      people.get().getBooksList().forEach(book -> {
        long minutes = java.time.Duration.between(book.getTakenAt().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime(),
            new Timestamp(new Date().getTime()).toLocalDateTime()).toMinutes();
        book.setFlag(minutes >= 14_400);
      });

      return people.get().getBooksList();
    } else {
      return Collections.emptyList();
    }
  }
}
