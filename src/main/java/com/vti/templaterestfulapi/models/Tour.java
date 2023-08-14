package com.vti.templaterestfulapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Tour {
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "tour_sequence";
    private String name; // ten tour
    private String code; // ma tour
    private double price; // gia tour
    private Date startTime; // Ngay khoi hanh
    private Date finishTime; // Ngay ket thuc
    private int maxSlot; // So luong toi da cho
    private List<Booking> bookingList;
    private int status;
    private List<String> listImages; // list url images
    private String desc;
    private Date createdDate;

}
