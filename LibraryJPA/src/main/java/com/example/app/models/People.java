package com.example.app.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * Represents a person in the application.
 */
@Entity
@Data
@Table(name = "People")
public class People {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotEmpty(message = "Поле ПІБ не повинно бути пустим")
  @Pattern(regexp = "[А-ЯІЇЄ][а-яіїє]+ [А-ЯІЇЄ][а-яіїє]+ [А-ЯІЇЄ][а-яіїє]+",
      message = "Поле ПІБ повинно бути у форматі: Прізвище Ім'я По-батькові")
  @Column(name = "fullName")
  private String fullName;

  @Min(value = 10, message = "Вік повинен бути більше 10")
  @Column(name = "age")
  private int age;

  @OneToMany(mappedBy = "people")
  private List<Books> booksList;
}
