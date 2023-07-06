package com.vti.templaterestfulapi.service;

import com.vti.templaterestfulapi.models.Category;
import com.vti.templaterestfulapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Configurable
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public Category insert(Category category){

        category.setId(sequenceGeneratorService.generateSequence(Category.SEQUENCE_NAME));
        category.setCreateDate(new Date());
        category.setActive(true);
        return categoryRepository.insert(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAllByActive(true);
    }
}
