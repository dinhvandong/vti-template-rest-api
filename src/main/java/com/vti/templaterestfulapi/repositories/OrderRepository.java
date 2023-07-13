package com.vti.templaterestfulapi.repositories;

import com.vti.templaterestfulapi.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findAllByUserID(long userID);
}
