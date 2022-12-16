package com.example.app.controllers;

import com.example.app.models.People;
import com.example.app.services.PeopleServices;
import com.example.app.util.PeopleValidator;
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
 * Controller for handling person-related operations and views.
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

  private final PeopleServices peopleServices;
  private final PeopleValidator peopleValidator;

  @Autowired
  public PeopleController(PeopleServices peopleServices, PeopleValidator peopleValidator) {
    this.peopleServices = peopleServices;
    this.peopleValidator = peopleValidator;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("show_people", peopleServices.findAll());
    return "people/index";
  }

  /**
  * Retrieves and displays details of a specific person.
  *
  * @param id    the ID of the person to display
  * @param model the Model object for storing data to be displayed in the view
  * @return the name of the view template
  */
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("people", peopleServices.findOne(id));
    model.addAttribute("books", peopleServices.forDelay(id));
    return "people/show";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("people") People person) {
    return "people/new";
  }

  /**
  * Handles the creation of a new person.
  *
  * @param people         the person to create
  * @param bindingResult  the binding result for validation
  * @return the name of the view template or a redirection URL
  */
  @PostMapping()
  public String create(@ModelAttribute("people") @Valid People people,
                       BindingResult bindingResult) {

    peopleValidator.validate(people, bindingResult);

    if (bindingResult.hasErrors()) {
      return "people/new";
    }

    peopleServices.save(people);
    return "redirect:/people";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") int id) {
    model.addAttribute("people", peopleServices.findOne(id));
    return "people/edit";
  }

  /**
  * Handles the update of an existing person.
  *
  * @param people         the updated person data
  * @param bindingResult  the binding result for validation
  * @param id             the ID of the person to update
  * @return the name of the view template or a redirection URL
  */
  @PatchMapping("/{id}")
  public String update(@ModelAttribute("people") @Valid People people,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {

    //peopleValidator.validate(people,bindingResult);

    if (bindingResult.hasErrors()) {
      return "people/edit";
    }

    peopleServices.update(id, people);
    return "redirect:/people";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    peopleServices.delete(id);
    return "redirect:/people";
  }
}
