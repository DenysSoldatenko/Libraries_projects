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
 * Data Access Object (DAO) for interacting with Books data in the database.
 */
@Component
public class BooksDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BooksDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Books> index() {
    return jdbcTemplate.query("SELECT * FROM Books",
      new BeanPropertyRowMapper<>(Books.class));
  }

  public Books show(int id) {
    return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id},
      new BeanPropertyRowMapper<>(Books.class)).stream().findAny().orElse(null);
  }

  public void save(Books book) {
    jdbcTemplate.update("INSERT INTO Books(name, author, year) VALUES(?, ?, ?)",
        book.getName(), book.getAuthor(), book.getYear());
  }

  public void update(int id, Books updated) {
    jdbcTemplate.update("UPDATE Books SET name=?, author=?, year=? WHERE id=?",
        updated.getName(), updated.getAuthor(), updated.getYear(), id);
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
  }


  public void assignBook(int id, int idPerson) {
    jdbcTemplate.update("UPDATE Books set id_people=? WHERE id=?", idPerson, id);
  }

  public void releaseBook(int id) {
    jdbcTemplate.update("UPDATE Books set id_people=null WHERE id=?", id);
  }

  /**
   * Retrieves the owner (People) of a specific book by its unique identifier.
   *
   * @param id the unique identifier of the book
   * @return an Optional containing the owner (People) of the book,
   *     if found; otherwise, an empty Optional
   */
  public Optional<People> getOwner(int id) {
    return jdbcTemplate.query(
    "SELECT People.* from Books join People on Books.id_people=People.id where Books.id=? ",
      new Object[]{id},
      new BeanPropertyRowMapper<>(People.class)).stream().findAny();
  }

  public Optional<Books> showFullNameOfBook(String name) {
    return jdbcTemplate.query("SELECT b.name from Books b where name=?", new Object[]{name},
      new BeanPropertyRowMapper<>(Books.class)).stream().findAny();
  }
}