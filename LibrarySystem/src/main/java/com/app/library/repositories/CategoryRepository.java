package com.app.library.repositories;

import com.app.library.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Category entities.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
