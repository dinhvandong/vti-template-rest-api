package com.vti.templaterestfulapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private  Long id;
    private  String comment;
    private List<String> urlImages;
}
