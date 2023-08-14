package com.vti.templaterestfulapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";
    List<User> userList;
    private String username ;
    private String phone;
    private String email;
    private String address;
    private String idPerson;
    private  String note;
    private Date createdDate; // Ngay tao booking
    private double deposite; // tien dat coc
    private Payment payment;
    private double price;
    private int status;


}
