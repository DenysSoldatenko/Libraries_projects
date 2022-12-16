package com.example.app.controllers;

import com.example.app.models.Books;
import com.example.app.models.People;
import com.example.app.services.BooksServices;
import com.example.app.services.PeopleServices;
import com.example.app.util.BooksValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling books-related operations and views.
 */
@Controller
@RequestMapping("/books")
public class BooksController {

  private final PeopleServices peopleServices;
  private final BooksServices booksServices;
  private final BooksValidator booksValidator;

  /**
  * Constructor to initialize the BooksController with required dependencies.
  *
  * @param booksValidator the validator for books
  * @param peopleServices the services for people
  * @param booksServices  the services for books
  */
  @Autowired
  public BooksController(BooksValidator booksValidator,
                         PeopleServices peopleServices, BooksServices booksServices) {
    this.peopleServices = peopleServices;
    this.booksServices = booksServices;
    this.booksValidator = booksValidator;
  }

  /**
  * Displays a list of books with optional pagination and sorting.
  *
  * @param model         the Model object for storing data to be displayed in the view
  * @param page          the current page number (optional)
  * @param booksPerPage  the number of books per page (optional)
  * @param sortByYear    flag to sort books by year (optional)
  * @return the name of the view template
  */
  @GetMapping()
  public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                     @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                     @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {

    if (page == null || booksPerPage == null) {
      model.addAttribute("show_books", booksServices.findAll(sortByYear));
    } else {
      model.addAttribute("show_books",
          booksServices.findWithPagination(page, booksPerPage, sortByYear));
    }

    return "books/index";
  }

  /**
  * Displays details of a specific book.
  *
  * @param id      the ID of the book to display
  * @param model   the Model object for storing data to be displayed in the view
  * @param people  a model attribute for holding people data
  * @return the name of the view template
  */
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model,
                     @ModelAttribute("person") People people) {
    model.addAttribute("books", booksServices.findOne(id));

    People bookOwner = booksServices.getBookOwner(id);

    if (bookOwner != null) {
      model.addAttribute("owner", bookOwner);
    } else {
      model.addAttribute("people", peopleServices.findAll());
    }
    return "books/show";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("books") Books books) {
    return "books/new";
  }

  /**
  * Handles the creation of a new book.
  *
  * @param books         the book data to create
  * @param bindingResult the binding result for validation
  * @return the name of the view template or a redirection URL
  */
  @PostMapping()
  public String create(@ModelAttribute("books") @Valid Books books,
                       BindingResult bindingResult) {

    booksValidator.validate(books, bindingResult);

    if (bindingResult.hasErrors()) {
      return "books/new";
    }

    booksServices.save(books);
    return "redirect:/books";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") int id) {
    model.addAttribute("books", booksServices.findOne(id));
    return "books/edit";
  }

  /**
  * Handles the update of an existing book.
  *
  * @param books         the updated book data
  * @param bindingResult the binding result for validation
  * @param id            the ID of the book to update
  * @return the name of the view template or a redirection URL
  */
  @PatchMapping("/{id}")
  public String update(@ModelAttribute("books") @Valid Books books,
                       BindingResult bindingResult, @PathVariable("id") int id) {

    //peopleValidator.validate(people,bindingResult);

    if (bindingResult.hasErrors()) {
      return "books/edit";
    }

    booksServices.update(id, books);
    return "redirect:/books";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    booksServices.delete(id);
    return "redirect:/books";
  }

  @PatchMapping("/{id}/saveBooks")
  public String saveBook(@PathVariable("id") int id, @ModelAttribute("person") People people) {
    booksServices.assignBook(id, people);
    return "redirect:/books/" + id;
  }

  @PatchMapping("/{id}/releaseBooks")
  public String releaseBook(@PathVariable("id") int id) {
    booksServices.releaseBook(id);
    return "redirect:/books/" + id;
  }

  @GetMapping("/search")
  public String search() {
    return "books/search";
  }

  @PostMapping("/search")
  public String searchBook(Model model, @RequestParam("name") String name) {
    model.addAttribute("books", booksServices.findAllByNameStartingWith(name));
    return "books/search";
  }
}

