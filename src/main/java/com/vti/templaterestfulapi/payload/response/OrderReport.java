package com.vti.templaterestfulapi.payload.response;

import com.vti.templaterestfulapi.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReport {
    private Date date;
    private List<Order> orderList;

}
/*
* {
* date:"2023-07-10",
* "orderList": [
*  {
* orderID:1,
* productList:[
* ]
* }
* ]
* }
*
* */