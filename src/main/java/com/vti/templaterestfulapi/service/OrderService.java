package com.vti.templaterestfulapi.service;

import com.vti.templaterestfulapi.models.Order;
import com.vti.templaterestfulapi.models.Product;
import com.vti.templaterestfulapi.models.ProductType;
import com.vti.templaterestfulapi.payload.response.OrderReport;
import com.vti.templaterestfulapi.payload.response.ProductReport;
import com.vti.templaterestfulapi.payload.response.ProductTypeReport;
import com.vti.templaterestfulapi.repositories.OrderRepository;
import com.vti.templaterestfulapi.repositories.ProductTypeRepository;
import com.vti.templaterestfulapi.utils.DateUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


    public Order insert(Order order)
    {
        return orderRepository.insert(order);
    }

    public Order update(Order order){
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if(optionalOrder.isPresent()){
            Order orderOld = optionalOrder.get();
            orderOld.setAddress(order.getAddress());
            orderOld.setPhoneNumber(order.getPhoneNumber());
            orderOld.setStatus(order.getStatus());
            return orderRepository.save(orderOld);
        }
        return null;
    }
    public List<Order> findAll()
    {
        return orderRepository.findAll();
    }
    public List<Order> findByUserID(long userID){
        return orderRepository.findAllByUserID(userID);
    }
    public Order findByID(long orderID)
    {
        Optional<Order> optionalOrder = orderRepository.findById(orderID);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        return null;
    }
    // 1-Lay bao cao ve so luong Order theo tung ngay + hien thi doanh thu
    // Ngay 13/07 100 - doanh thu 100 trieu
    // Ngay 14/007 200 - doanh thu 500 trieu
    // Ngay 15/07 500 - doanh thu 1 ty

    // 2- Bao cao san pham ban chay (so luong) theo ngay - tuan - thang - nam
    // Bao cao doanh thu theo loai san pham Dell 1 ty Asus 2 ty Lenovo 500 trieu

    public List<Order> findByDate(Date date)
    {
        List<Order> listAll = findAll();
        List<Order> returnList = findAll();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String foundDate = df.format(date); //2023-07-13
        for(Order order: listAll)
        {
            String orderDate = df.format(order.getCreatedDate());
            // 2023-07-13
            if(foundDate.equals(orderDate))
            {
                returnList.add(order);
            }
        }
        return returnList;
    }




}
