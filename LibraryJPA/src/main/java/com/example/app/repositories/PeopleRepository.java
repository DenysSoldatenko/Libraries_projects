package com.example.app.repositories;

import com.example.app.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing person entities.
 */
@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
  People getPeopleByFullName(String string);
}
