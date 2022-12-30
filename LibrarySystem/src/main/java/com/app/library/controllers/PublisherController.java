package com.app.library.controllers;

import com.app.library.models.Publisher;
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
 * Controller class for managing Publisher-related operations.
 */
@Controller
public class PublisherController {
  private final PublisherService publisherService;

  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping("/publishers")
  public String findAllPublishers(Model model) {
    model.addAttribute("publishers", publisherService.findAllPublishers());
    return "publisher/publishers";
  }

  @GetMapping("/add-publisher")
  public String addPublisher(@ModelAttribute("publisher") Publisher publisher) {
    return "publisher/publisher_add";
  }

  /**
  * Handles the POST request to save a new publisher.
  *
  * @param publisher      The Publisher object to be added (bound from the form)
  * @param bindingResult  BindingResult for handling validation errors
  * @return               The view name to redirect to, depending on the outcome
  */
  @PostMapping("/save-new-publisher")
  public String add(@ModelAttribute("publisher") @Valid Publisher publisher,
                    BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "publisher/publisher_add";
    }

    publisherService.savePublisher(publisher);
    return "redirect:/publishers";
  }

  @GetMapping("/update-publisher/{id}")
  public String updatePublisher(@PathVariable("id") int id, Model model) {
    model.addAttribute("publisher", publisherService.findPublishersById(id));
    return "publisher/publisher_update";
  }

  /**
  * Handles the POST request to update an existing publisher.
  *
  * @param publisher      The Publisher object with updated data (bound from the form)
  * @param bindingResult  BindingResult for handling validation errors
  * @param id             The unique identifier of the publisher to be updated
  * @return               The view name to redirect to, depending on the outcome
  */
  @PostMapping("/save-updated-publisher/{id}")
  public String update(@ModelAttribute("publisher") @Valid Publisher publisher,
                       BindingResult bindingResult, @PathVariable("id") int id) {

    if (bindingResult.hasErrors()) {
      return "publisher/publisher_update";
    }

    publisherService.updatePublisher(id, publisher);
    return "redirect:/publishers";
  }

  @GetMapping("/remove-publisher/{id}")
  public String removePublisher(@PathVariable("id") int id) {
    publisherService.deletePublisher(id);
    return "redirect:/publishers";
  }
}
