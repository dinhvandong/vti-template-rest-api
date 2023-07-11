package com.vti.templaterestfulapi.service;

import com.vti.templaterestfulapi.models.Category;
import com.vti.templaterestfulapi.models.ProductType;
import com.vti.templaterestfulapi.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Configurable
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    public ProductType insert(ProductType productType){

        productType.setId(sequenceGeneratorService.generateSequence(ProductType.SEQUENCE_NAME));
        productType.setCreateDate(new Date());
        productType.setActive(true);
        return productTypeRepository.insert(productType);
    }

    public List<ProductType> findAll(){
        return productTypeRepository.findAllByActive(true);
    }
}
