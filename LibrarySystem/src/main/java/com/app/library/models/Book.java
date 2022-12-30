package com.app.library.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a book in the library.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", length = 100, nullable = false)
  @NotEmpty(message = "Name should not be empty")
  private String name;

  @Column(name = "description", length = 250, nullable = false)
  @NotEmpty(message = "Description should not be empty")
  private String description;

  //@ManyToMany(cascade = CascadeType.ALL)
  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(name = "Books_authors",
      joinColumns = {@JoinColumn(name = "book_id")},
      inverseJoinColumns = {@JoinColumn(name = "author_id")})
  private Set<Author> authors = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(name = "Books_categories",
      joinColumns = {@JoinColumn(name = "book_id")},
      inverseJoinColumns = {@JoinColumn(name = "category_id")})
  private Set<Category> categories = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(name = "Books_publishers",
      joinColumns = {@JoinColumn(name = "book_id")},
      inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
  private Set<Publisher> publishers = new HashSet<>();

  public Book(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public void removePublisher(Publisher publisher) {
    this.publishers.remove(publisher);
    publisher.getBooks().remove(publisher);
  }

  public void addPublisher(Publisher publisher) {
    this.publishers.add(publisher);
    publisher.getBooks().add(this);
  }

  public void removeAuthor(Author author) {
    this.authors.remove(author);
    author.getBooks().remove(author);
  }

  public void addAuthor(Author author) {
    this.authors.add(author);
    author.getBooks().add(this);
  }

  public void removeCategory(Category category) {
    this.categories.remove(category);
    category.getBooks().remove(category);
  }

  public void addCategory(Category category) {
    this.categories.add(category);
    category.getBooks().add(this);
  }
}
