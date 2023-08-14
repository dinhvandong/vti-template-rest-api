package com.vti.templaterestfulapi.repositories;


import com.vti.templaterestfulapi.models.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TourRepository extends MongoRepository<Tour, Long> {
}
