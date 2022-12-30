package com.app.library.controllers;

import com.app.library.models.Category;
import com.app.library.services.CategoryService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for managing Category-related operations.
 */
@Controller
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public String findAllBooks(Model model) {
    model.addAttribute("categories", categoryService.findAllCategories());
    return "category/categories";
  }

  @GetMapping("/add-category")
  public String addCategory(@ModelAttribute("category") Category category) {
    return "category/category_add";
  }

  /**
  * Handles the POST request to save a new category.
  *
  * @param category       The Category object to be added (bound from the form)
  * @param bindingResult  BindingResult for handling validation errors
  * @return               The view name to redirect to, depending on the outcome
  */
  @PostMapping("/save-new-category")
  public String add(@ModelAttribute("category") @Valid Category category,
                    BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "category/category_add";
    }

    categoryService.saveCategory(category);
    return "redirect:/categories";
  }

  @GetMapping("/update-category/{id}")
  public String updateCategory(@PathVariable("id") int id, Model model) {
    model.addAttribute("category", categoryService.findCategoryById(id));
    return "category/category_update";
  }

  /**
  * Handles the POST request to update an existing category.
  *
  * @param category       The Category object with updated data (bound from the form)
  * @param bindingResult  BindingResult for handling validation errors
  * @param id             The unique identifier of the category to be updated
  * @return               The view name to redirect to, depending on the outcome
  */
  @PostMapping("/save-updated-category/{id}")
  public String update(@ModelAttribute("category") @Valid Category category,
                       BindingResult bindingResult, @PathVariable("id") int id) {

    if (bindingResult.hasErrors()) {
      return "category/category_update";
    }

    categoryService.updateCategory(id, category);
    return "redirect:/categories";
  }

  @GetMapping("/remove-category/{id}")
  public String removeCategory(@PathVariable("id") int id) {
    categoryService.deleteCategory(id);
    return "redirect:/categories";
  }

}

