package com.vti.templaterestfulapi.controller;

import com.vti.templaterestfulapi.models.Category;
import com.vti.templaterestfulapi.models.ProductType;
import com.vti.templaterestfulapi.models.ResponseObject;
import com.vti.templaterestfulapi.service.CategoryService;
import com.vti.templaterestfulapi.service.ProductTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productType")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;


    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody ProductType productType) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        productTypeService.insert(productType)));

    }
//
//    @PostMapping("/update")
//    public ResponseEntity<?> update(@Valid @RequestBody Category category) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject(200, "Ok",
//                        categoryService.update(category)));
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<?> delete( @RequestBody Category category) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject(200, "Ok",
//                        categoryService.delete(category)));
//    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        productTypeService.findAll()));
    }
}
