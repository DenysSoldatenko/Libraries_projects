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
 * Represents a category for books in the library.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class Category {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", length = 50, nullable = false, unique = true)
  @NotEmpty(message = "Name should not be empty")
  private String name;

  @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
  private Set<Book> books = new HashSet<>();

  public Category(String name) {
    this.name = name;
  }
}
