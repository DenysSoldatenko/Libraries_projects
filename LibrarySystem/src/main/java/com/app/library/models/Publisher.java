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
 * Represents a publisher of books.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Publishers")
public class Publisher {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", length = 100, nullable = false, unique = true)
  @NotEmpty(message = "Name should not be empty")
  private String name;

  @ManyToMany(mappedBy = "publishers", cascade = CascadeType.ALL)
  private Set<Book> books = new HashSet<>();

  public Publisher(String name) {
    this.name = name;
  }
}
