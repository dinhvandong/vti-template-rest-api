package com.vti.templaterestfulapi.payload.response;

import com.vti.templaterestfulapi.models.Product;
import com.vti.templaterestfulapi.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeReport {
    private String productType;
    List<Product> productList;

}
