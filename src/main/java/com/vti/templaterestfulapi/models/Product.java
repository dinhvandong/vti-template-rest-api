package com.vti.templaterestfulapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    private Long id;
    private String name;
    private double price;
    private String description;
    private List<String> listURL;
    private Long categoryID;
    private Long productType;
    private int factoryID;
    private Date createdDate;
    private Date deleteTime;
    private boolean active;
    private Date createDate;// NULL chua xoa != NULL xoa  - Thoi gian xoa
}