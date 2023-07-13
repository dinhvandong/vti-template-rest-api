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

    @Autowired
    ProductTypeRepository productTypeRepository;

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

    public List<OrderReport> findAllByDate(Date fromDate, Date toDate){
        List<Date> arrayDate = DateUtils.getDatesBetween(fromDate, toDate);
        List<OrderReport> reportList = new ArrayList<>();
        for(Date date: arrayDate)
        {
            List<Order> orderList = findByDate(date);
            OrderReport orderReport = new OrderReport();
            orderReport.setDate(date);
            orderReport.setOrderList(orderList);
            reportList.add(orderReport);
        }
        return reportList;
    }

    public List<ProductReport> findAllProductReportByDate(Date fromDate, Date toDate)
    {
        List<Date> arrayDate = DateUtils.getDatesBetween(fromDate, toDate);
        List<ProductReport> reportList = new ArrayList<ProductReport>();
        for(Date date: arrayDate)
        {
            List<Product> productList = findProductByDate(date);
            ProductReport  productReport = new ProductReport();
            productReport.setDate(date);
            productReport.setProductList(productList);
            reportList.add(productReport);
        }
        return reportList;
    }

    public List<ProductTypeReport> findAllProductTypeReportByDate
            (Date fromDate, Date toDate)
    {
        List<ProductTypeReport> reportList = new ArrayList<>();
        List<ProductType> listType = productTypeRepository.findAll();
        for(ProductType type: listType){
            ProductTypeReport  report = new ProductTypeReport();
            report.setProductType(type.getName());
            List<Product> productList = findProductTypeFromDateToDate(fromDate, toDate, type.getId());
            report.setProductList(productList);
            reportList.add(report);
        }
        return reportList;
    }

    // Dell - 100 cai - doanh thu 1 ty
    // HP - 500 c - doanh thu 5 ty
    // Lenovo - 40 - doanh thu 400 trieu
    // Asus - 500 - doanh thu 10 ty

    List<Product> findProductTypeFromDateToDate(Date fromDate, Date toDate, long productType)
    {
        List<Product> returnList = new ArrayList<>();
        List<Date> arrayDate = DateUtils.getDatesBetween(fromDate, toDate);
        for(Date date: arrayDate)
        {
            returnList.addAll(findProductTypeByDate(date, productType));
        }
        return returnList;
    }
    List<Product> findProductTypeByDate(Date date, long productType)
    {
        List<Product> productList = findProductByDate(date);

        List<Product> productArray = new ArrayList<>();
        for(Product product: productList)
        {
            if(product.getProductType()== productType){
                productArray.add(product);
            }
        }
        return productArray;
    }

    public  List<Product> findProductByDate(Date date){
        List<Order> orderList = findByDate(date);
        List<Product> listAll = new ArrayList<>();
        for(Order order: orderList)
        {
            listAll.addAll(order.getProductList());
        }
        return  listAll;
    }
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
