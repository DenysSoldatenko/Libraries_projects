package com.app.library.controllers;

import com.app.library.models.Author;
import com.app.library.services.AuthorService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for managing Author-related operations.
 */
@Controller
public class AuthorController {
  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/authors")
  public String findAllAuthors(Model model) {
    model.addAttribute("authors", authorService.findAllAuthors());
    return "author/authors";
  }

  @GetMapping("/add-author")
  public String addAuthor(@ModelAttribute("author") Author author) {
    return "author/author_add";
  }

  /**
  * Handles the submission of a new author.
  *
  * @param author         the author model attribute
  * @param bindingResult  the binding result for validation
  * @return the redirection URL or view name
  */
  @PostMapping("/save-new-author")
  public String add(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "author/author_add";
    }

    authorService.saveAuthor(author);
    return "redirect:/authors";
  }

  @GetMapping("/update-author/{id}")
  public String updateAuthor(@PathVariable("id") int id, Model model) {
    model.addAttribute("author", authorService.findAuthorsById(id));
    return "author/author_update";
  }

  /**
  * Handles the submission of an updated author.
  *
  * @param author         the author model attribute
  * @param bindingResult  the binding result for validation
  * @param id             the ID of the author to update
  * @return the redirection URL or view name
  */
  @PostMapping("/save-updated-author/{id}")
  public String update(@ModelAttribute("author") @Valid Author author,
                       BindingResult bindingResult, @PathVariable("id") int id) {

    if (bindingResult.hasErrors()) {
      return "author/author_update";
    }

    authorService.updateAuthor(id, author);
    return "redirect:/authors";
  }

  @GetMapping("/remove-author/{id}")
  public String removeAuthor(@PathVariable("id") int id) {
    authorService.deleteAuthor(id);
    return "redirect:/authors";
  }
}
