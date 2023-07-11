package com.vti.templaterestfulapi.controller;

import com.vti.templaterestfulapi.models.Category;
import com.vti.templaterestfulapi.models.ResponseObject;
import com.vti.templaterestfulapi.models.Role;
import com.vti.templaterestfulapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody Category category) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        categoryService.insert(category)));

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Category category) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        categoryService.update(category)));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete( @RequestBody Category category) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        categoryService.delete(category)));
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        categoryService.findAll()));
    }

}
