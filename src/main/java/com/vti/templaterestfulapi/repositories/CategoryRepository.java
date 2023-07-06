package com.vti.templaterestfulapi.repositories;

import com.vti.templaterestfulapi.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, Long> {

    List<Category> findAllByActive(boolean active);
}
