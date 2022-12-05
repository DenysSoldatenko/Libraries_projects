package com.example.app.controllers;

import com.example.app.dao.BooksDao;
import com.example.app.dao.PeopleDao;
import com.example.app.models.Books;
import com.example.app.models.People;
import com.example.app.util.BooksValidator;
import java.util.Optional;
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

/**
 * Controller for managing books in the application.
 */
@Controller
@RequestMapping("/books")
public class BooksController {

  private final BooksDao booksDao;
  private final PeopleDao peopleDao;
  private final BooksValidator booksValidator;

  /**
   * Constructor to initialize the BooksController with the necessary dependencies.
   *
   * @param booksDao      the BooksDao instance
   * @param booksValidator the BooksValidator instance
   * @param peopleDao     the PeopleDao instance
   */
  @Autowired
  public BooksController(BooksDao booksDao, BooksValidator booksValidator, PeopleDao peopleDao) {
    this.booksDao = booksDao;
    this.booksValidator = booksValidator;
    this.peopleDao = peopleDao;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("show_books", booksDao.index());
    return "books/index";
  }

  /**
   * Displays detailed information about a specific book.
   *
   * @param id    the unique identifier of the book
   * @param model the Model object to populate with data
   * @param people a People object used for form submission
   * @return the view name for the book details page
   */
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model,
                     @ModelAttribute("person") People people) {
    model.addAttribute("books", booksDao.show(id));
    Optional<People> bookOwner = booksDao.getOwner(id);

    if (bookOwner.isPresent()) {
      model.addAttribute("owner", bookOwner.get());
    } else {
      model.addAttribute("people", peopleDao.index());
    }
    return "books/show";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("books") Books books) {
    return "books/new";
  }

  /**
   * Handles the submission of a new book creation form.
   *
   * @param books          a Books object containing the book data
   * @param bindingResult  the BindingResult object for handling validation errors
   * @return the view name for redirecting after book creation
   */
  @PostMapping()
  public String create(@ModelAttribute("books") @Valid Books books,
                       BindingResult bindingResult) {

    booksValidator.validate(books, bindingResult);

    if (bindingResult.hasErrors()) {
      return "books/new";
    }

    booksDao.save(books);
    return "redirect:/books";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") int id) {
    model.addAttribute("books", booksDao.show(id));
    return "books/edit";
  }

  /**
   * Handles the update of book information.
   *
   * @param books         a Books object containing the updated book data
   * @param bindingResult the BindingResult object for handling validation errors
   * @param id            the unique identifier of the book to be updated
   * @return the view name for redirecting after the update,
   *     or the edit view if there are validation errors
   */
  @PatchMapping("/{id}")
  public String update(@ModelAttribute("books") @Valid Books books, BindingResult bindingResult,
                       @PathVariable("id") int id) {

    //peopleValidator.validate(people,bindingResult);

    if (bindingResult.hasErrors()) {
      return "books/edit";
    }

    booksDao.update(id, books);
    return "redirect:/books";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    booksDao.delete(id);
    return "redirect:/books";
  }

  @PatchMapping("/{id}/saveBooks")
  public String saveBook(@PathVariable("id") int id, @ModelAttribute("person") People people) {
    booksDao.assignBook(id, people.getId());
    return "redirect:/books/" + id;
  }

  @PatchMapping("/{id}/releaseBooks")
  public String releaseBook(@PathVariable("id") int id) {
    booksDao.releaseBook(id);
    return "redirect:/books/" + id;
  }
}
