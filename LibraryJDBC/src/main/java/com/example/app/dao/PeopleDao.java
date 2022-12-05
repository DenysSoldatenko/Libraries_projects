package com.example.app.dao;

import com.example.app.models.Books;
import com.example.app.models.People;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Data Access Object (DAO) for interacting with People data in the database.
 */
@Component
public class PeopleDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PeopleDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<People> index() {
    return jdbcTemplate.query("SELECT * FROM People", new BeanPropertyRowMapper<>(People.class));
  }

  public People show(int id) {
    return jdbcTemplate.query("SELECT * FROM People WHERE id=?", new Object[]{id},
      new BeanPropertyRowMapper<>(People.class)).stream().findAny().orElse(null);
  }

  public void save(People people) {
    jdbcTemplate.update("INSERT INTO People(full_name, age) VALUES(?, ?)",
        people.getFullName(), people.getAge());
  }

  public void update(int id, People updated) {
    jdbcTemplate.update("UPDATE People SET full_name=?, age=? WHERE id=?",
        updated.getFullName(), updated.getAge(), id);
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM People WHERE id=?", id);
  }

  public Optional<People> showFullName(String fullName) {
    return jdbcTemplate.query("SELECT * FROM People WHERE full_name=?", new Object[]{fullName},
      new BeanPropertyRowMapper<>(People.class)).stream().findAny();
  }

  public List<Books> showBook(int id) {
    return jdbcTemplate.query("select * from Books where id_people=?", new Object[]{id},
      new BeanPropertyRowMapper<>(Books.class));
  }
}
