package com.vti.templaterestfulapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Category {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "category_sequence";

    private String name;
    private String code;
    private String desc;
    private boolean active;
    private Date createDate;

}
