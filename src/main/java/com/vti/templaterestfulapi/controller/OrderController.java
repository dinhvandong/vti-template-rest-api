package com.vti.templaterestfulapi.controller;

import com.vti.templaterestfulapi.models.Order;
import com.vti.templaterestfulapi.models.Product;
import com.vti.templaterestfulapi.models.ResponseObject;
import com.vti.templaterestfulapi.models.User;
import com.vti.templaterestfulapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        orderService.insert(order)));
    }
    @PostMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        orderService.findAll()));
    }
    @PostMapping("/findAllByUserID")
    public ResponseEntity<?> findAll(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        orderService.findByUserID(user.getId())));
    }

    @PostMapping("/findAllByUserID2")
    public ResponseEntity<?> findAll(@RequestParam long userID) {
        //findAllByUserID2?userID=1?orderID=100?email=dongdv@gmail.com
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Ok",
                        orderService.findByUserID(userID)));
    }





}
