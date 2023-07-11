package com.vti.templaterestfulapi.service;


import com.vti.templaterestfulapi.models.Product;
import com.vti.templaterestfulapi.models.ProductType;
import com.vti.templaterestfulapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Configurable
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public Product insert(Product product){
        product.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
        product.setCreateDate(new Date());
        product.setActive(true);
        return productRepository.insert(product);
    }

    public List<Product> findAll(){
        return productRepository.findAllByActive(true);
    }

}
