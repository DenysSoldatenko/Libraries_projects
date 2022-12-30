package com.app.library.controllers;

import com.app.library.models.Book;
import com.app.library.services.AuthorService;
import com.app.library.services.BookService;
import com.app.library.services.CategoryService;
import com.app.library.services.PublisherService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for managing Book-related operations.
 */
@Controller
public class BookController {
  private final BookService bookService;
  private final CategoryService categoryService;
  private final AuthorService authorService;
  private final PublisherService publisherService;

  /**
  * Constructor to initialize the BookController with the required services.
  *
  * @param bookService     the BookService instance
  * @param categoryService the CategoryService instance
  * @param authorService   the AuthorService instance
  * @param publisherService the PublisherService instance
  */
  public BookController(BookService bookService, CategoryService categoryService,
                        AuthorService authorService, PublisherService publisherService) {
    this.bookService = bookService;
    this.categoryService = categoryService;
    this.authorService = authorService;
    this.publisherService = publisherService;
  }

  @GetMapping("/books")
  public String findAllBooks(Model model) {
    model.addAttribute("books", bookService.findAllBooks());
    return "book/books";
  }

  @GetMapping("/book/{id}")
  public String findBook(@PathVariable("id") int id, Model model) {
    model.addAttribute("book", bookService.findBooksById(id));
    return "book/book_info";
  }

  /**
  * Displays the form for adding a new book.
  *
  * @param book  a new book to add (bound to the form)
  * @param model the model to which data is added for populating form fields
  * @return the view for adding a new book
  */
  @GetMapping("/add-book")
  public String addBook(@ModelAttribute("book") Book book, Model model) {
    model.addAttribute("categories", categoryService.findAllCategories());
    model.addAttribute("publishers", publisherService.findAllPublishers());
    model.addAttribute("authors", authorService.findAllAuthors());
    return "book/book_add";
  }

  /**
  * Saves a new book.
  *
  * @param book           the new book to save
  * @param bindingResult the result of the validation
  * @return a redirect to the list of books if successful, otherwise the added book form
  */
  @PostMapping("/save-new-book")
  public String add(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "book/book_add";
    }

    bookService.saveBook(book);
    return "redirect:/books";
  }

  /**
  * Displays the form for updating an existing book.
  *
  * @param id    the ID of the book to update
  * @param model the model to which book data is added for populating form fields
  * @return the view for updating an existing book
  */
  @GetMapping("/update-book/{id}")
  public String updateBook(@PathVariable("id") int id, Model model) {
    model.addAttribute("book", bookService.findBooksById(id));
    model.addAttribute("categories", categoryService.findAllCategories());
    model.addAttribute("publishers", publisherService.findAllPublishers());
    model.addAttribute("authors", authorService.findAllAuthors());
    return "book/book_update";
  }

  /**
   * Saves updates to an existing book.
   *
   * @param book           the updated book
   * @param bindingResult the result of the validation
   * @param id             the ID of the book to update
   * @return a redirect to the list of books if successful, otherwise the update book form
   */
  @PostMapping("/save-updated-book/{id}")
  public String update(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult, @PathVariable("id") int id) {

    if (bindingResult.hasErrors()) {
      return "book/book_update";
    }

    bookService.updateBook(id, book);
    return "redirect:/books";
  }

  @GetMapping("/remove-book/{id}")
  public String removeBook(@PathVariable("id") int id) {
    bookService.deleteBook(id);
    return "redirect:/books";
  }
}
