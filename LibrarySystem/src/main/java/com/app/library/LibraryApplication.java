package com.app.library;

import com.app.library.models.Author;
import com.app.library.models.Book;
import com.app.library.models.Category;
import com.app.library.models.Publisher;
import com.app.library.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The main class for starting the Library Application.
 */
@SpringBootApplication
public class LibraryApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryApplication.class, args);
  }

  /*
  @Bean
  public CommandLineRunner initialization(BookService bookService) {
    return (args -> {
      Book book1 = getBook1();
      bookService.saveBook(book1);

      Book book2 = getBook2();
      bookService.saveBook(book2);

      Book book3 = getBook3();
      bookService.saveBook(book3);

      Book book4 = getBook4();
      bookService.saveBook(book4);
    });
  }

  private static Book getBook4() {
    Book book4 = new Book("Charm Of The Fool", "A man discovers that's he's actually a robot.");
    Author author4 = new Author("Louis Knight", "Just a guy");
    Category category4 = new Category("Humor");
    Publisher publisher4 = new Publisher("Macmillan");
    book4.addAuthor(author4);
    book4.addCategory(category4);
    book4.addPublisher(publisher4);
    return book4;
  }

  private static Book getBook3() {
    Book book3 = new Book("Slave Of Crime",
        "The rivaling gangs have spiraled into an all out war, "
        + "soon the entire city will be a battleground "
        + "and the government apparently plans to surround the city and let the gangs fight it out."
    );
    Author author3 = new Author("Damian Emerson", "Physiologist with writings talent");
    Category category3 = new Category("Drama");
    Publisher publisher3 = new Publisher("Penguin Random House");
    book3.addAuthor(author3);
    book3.addCategory(category3);
    book3.addPublisher(publisher3);
    return book3;
  }

  private static Book getBook2() {
    Book book2 = new Book("Admiring The Moon",
        "The unexciting life of a teenage boy changes in an instant "
        + "as a new friend enters his life."
    );
    Author author2 = new Author("Janey Akhtar",
        "Young lady who loves adventures and writing about adventures");
    Category category2 = new Category("Adventure");
    Publisher publisher2 = new Publisher("Macmillan");
    book2.addAuthor(author2);
    book2.addCategory(category2);
    book2.addPublisher(publisher2);
    return book2;
  }

  private static Book getBook1() {
    Book book1 = new Book("Woman Without A Home",
        "A girl goes missing in the woods, and her parents find only "
        + "a decrepit and scary doll left behind. "
        + "They soon learn that the doll is actually their daughter. And she's alive.");
    Author author1 = new Author("Simon Abdi", "Future king of horror stories");
    Category category1 = new Category("Horror");
    Publisher publisher1 = new Publisher("Simon & Schuster");
    book1.addAuthor(author1);
    book1.addCategory(category1);
    book1.addPublisher(publisher1);
    return book1;
  }
  */
}
