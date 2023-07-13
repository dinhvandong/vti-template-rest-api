package com.vti.templaterestfulapi.payload.response;

import com.vti.templaterestfulapi.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReport {
    private Date date;
    private List<Product> productList;
}
