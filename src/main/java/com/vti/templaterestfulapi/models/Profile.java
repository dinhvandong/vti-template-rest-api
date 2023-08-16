package com.vti.templaterestfulapi.models;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Profile {

    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "profile_sequence";
    private String name;
    private String phone;
    private String address;
    private Date birthDay;
    private boolean gender;
    private String email;

}
