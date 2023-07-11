package com.vti.templaterestfulapi.controller;

import com.vti.templaterestfulapi.models.Product;
import com.vti.templaterestfulapi.models.ProductType;
import com.vti.templaterestfulapi.models.ResponseObject;
import com.vti.templaterestfulapi.service.ProductService;
import com.vti.templaterestfulapi.service.SequenceGeneratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        productService.insert(product)));
    }
    @PostMapping("/findAll")
        public ResponseEntity<?> findAll() {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Ok",
                            productService.findAll()));
        }
}
