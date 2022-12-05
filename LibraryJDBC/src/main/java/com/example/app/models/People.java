package com.example.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Represents a person with a unique identifier, full name, and age.
 */
public class People {
  private int id;
  @NotEmpty(message = "Поле ПІБ не повинно бути пустим")
  @Pattern(regexp = "[А-ЯІЇЄ][а-яіїє]+ [А-ЯІЇЄ][а-яіїє]+ [А-ЯІЇЄ][а-яіїє]+",
      message = "Поле ПІБ повинно бути у форматі: Прізвище Ім'я По-батькові")
  private String fullName;
  @Min(value = 10, message = "Вік повинен бути більше 10")
  private int age;

  /**
   * Creates a new instance of the People class with the specified properties.
   *
   * @param id The unique identifier of the person.
   * @param fullName The full name of the person.
   * @param age The age of the person.
   */
  public People(int id, String fullName, int age) {
    this.id = id;
    this.fullName = fullName;
    this.age = age;
  }

  public People() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
