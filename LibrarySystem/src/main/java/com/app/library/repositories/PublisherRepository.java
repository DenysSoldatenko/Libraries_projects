package com.app.library.repositories;

import com.app.library.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Publisher entities.
 */
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
