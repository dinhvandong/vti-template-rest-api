package com.vti.templaterestfulapi.service;


import com.vti.templaterestfulapi.models.Product;
import com.vti.templaterestfulapi.models.ProductType;
import com.vti.templaterestfulapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Configurable
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product insert(Product product){
        return productRepository.insert(product);
    }

    public List<Product> findAll(){
        return productRepository.findAllByActive(true);
    }

}
