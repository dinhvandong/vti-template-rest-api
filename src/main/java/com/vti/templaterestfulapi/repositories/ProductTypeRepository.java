package com.vti.templaterestfulapi.repositories;

import com.vti.templaterestfulapi.models.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductTypeRepository extends MongoRepository<ProductType, Long>
{
    List<ProductType> findAllByActive(boolean active);
}
