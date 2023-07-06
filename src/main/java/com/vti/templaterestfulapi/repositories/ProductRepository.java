package com.vti.templaterestfulapi.repositories;

import com.vti.templaterestfulapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long>
{

    List<Product> findAllByActive(boolean active);
}
