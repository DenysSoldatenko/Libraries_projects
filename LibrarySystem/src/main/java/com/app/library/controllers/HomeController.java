package com.app.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling home-related operations and views.
 */
@Controller
public class HomeController {
  @GetMapping("/")
  public String home() {
    return "home";
  }
}
