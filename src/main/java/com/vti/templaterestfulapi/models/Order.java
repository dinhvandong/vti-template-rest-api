package com.vti.templaterestfulapi.models;

import com.vti.templaterestfulapi.dto.DeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {
    // productID
    // int num
    // int userID
    // date
    // sum_price
    // voucher
    // delivery
    // address
    // status = pending /  processing / finish /
    // payment
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";
    private List<Product> productList;
    private Long userID;
    private String phoneNumber;
    private Address address;
    private Payment payment;
    private Delivery delivery;
    private Date createdDate;
    private Voucher voucher;
    private int status;
}
