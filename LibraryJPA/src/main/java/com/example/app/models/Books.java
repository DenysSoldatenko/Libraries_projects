package com.example.app.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Represents a book in the application.
 */
@Entity
@Data
@Table(name = "Books")
public class Books {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotEmpty(message = "Поле Ім'я книги не повинно бути пустим")
  @Size(min = 2, max = 50, message = "Поле Ім'я книги повинно знаходитись між 2 і 50 символами")
  @Column(name = "name")
  private String name;

  @NotEmpty(message = "Поле Автор не повинно бути пустим")
  @Pattern(regexp = "[А-ЯІЇЄ][а-яіїє]+ [А-ЯІЇЄ][а-яіїє]+",
      message = "Поле Автор повинно бути у форматі: Ім'я Прізвище")
  @Column(name = "author")
  private String author;

  @Min(value = 1900, message = "Рік видання повинен бути більше 1900")
  @Max(value = 2022, message = "Рік видання повинен бути менше 2022")
  @Column(name = "year")
  private int year;

  @ManyToOne
  @JoinColumn(name = "id_people", referencedColumnName = "id")
  private People people;

  @Column(name = "taken_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date takenAt;

  @Transient
  private boolean flag; // Змінна для перевірки терміну повернення книги
}
