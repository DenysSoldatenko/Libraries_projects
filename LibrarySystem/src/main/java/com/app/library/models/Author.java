package com.app.library.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an author of books in the library.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Authors")
public class Author {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", length = 100, nullable = false, unique = true)
  @NotEmpty(message = "Name should not be empty")
  private String name;

  @Column(name = "description", length = 250, nullable = false)
  @NotEmpty(message = "Description should not be empty")
  private String description;

  @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
  private Set<Book> books = new HashSet<>();

  public Author(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
