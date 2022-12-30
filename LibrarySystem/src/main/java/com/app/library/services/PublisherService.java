package com.app.library.services;

import com.app.library.models.Publisher;
import com.app.library.repositories.PublisherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Publisher-related operations.
 */
@Service
public class PublisherService {
  private final PublisherRepository publisherRepository;

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public List<Publisher> findAllPublishers() {
    return publisherRepository.findAll();
  }

  public Publisher findPublishersById(int id) {
    return publisherRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Publisher not found"));
  }

  public void savePublisher(Publisher publisher) {
    publisherRepository.save(publisher);
  }

  public void deletePublisher(int id) {
    publisherRepository.deleteById(id);
  }

  public void updatePublisher(int id, Publisher publisher) {
    publisher.setId(id);
    publisherRepository.save(publisher);
  }
}
