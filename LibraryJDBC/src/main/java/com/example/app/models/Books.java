package com.example.app.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Represents a book in the application.
 */
public class Books {
  private int id;
  @NotEmpty(message = "Поле Ім'я книги не повинно бути пустим")
  @Size(min = 2, max = 50, message = "Поле Ім'я книги повинно знаходитись між 2 і 50 символами")
  private String name;
  @NotEmpty(message = "Поле Автор не повинно бути пустим")
  @Pattern(regexp = "[А-ЯІЇЄ][а-яіїє]+ [А-ЯІЇЄ][а-яіїє]+",
      message = "Поле Автор повинно бути у форматі: Ім'я Прізвище")
  private String author;
  @Min(value = 1900, message = "Рік видання повинен бути більше 1900")
  @Max(value = 2022, message = "Рік видання повинен бути менше 2022")
  private int year;

  /**
   * Constructs a new book with the specified values.
   *
   * @param id     the unique identifier of the book
   * @param name   the name of the book
   * @param author the author of the book
   * @param year   the year of publication
   */
  public Books(int id, String name, String author, int year) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.year = year;
  }

  public Books() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
