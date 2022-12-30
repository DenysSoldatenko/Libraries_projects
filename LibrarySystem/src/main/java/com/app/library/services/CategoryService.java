package com.app.library.services;

import com.app.library.models.Category;
import com.app.library.repositories.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Category-related operations.
 */
@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> findAllCategories() {
    return categoryRepository.findAll();
  }

  public Category findCategoryById(int id) {
    return categoryRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Category not found"));
  }

  public void saveCategory(Category category) {
    categoryRepository.save(category);
  }

  public void deleteCategory(int id) {
    categoryRepository.deleteById(id);
  }

  public void updateCategory(int id, Category category) {
    category.setId(id);
    categoryRepository.save(category);
  }
}
