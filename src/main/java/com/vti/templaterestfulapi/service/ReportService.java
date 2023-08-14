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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductTypeRepository productTypeRepository;

    public List<OrderReport> findAllByDate(Date fromDate, Date toDate){
        List<Date> arrayDate = DateUtils.getDatesBetween(fromDate, toDate);
        List<OrderReport> reportList = new ArrayList<>();
        for(Date date: arrayDate)
        {
            List<Order> orderList = orderService.findByDate(date);
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
    // Nghiep vụ
    // THông kê từ ngày đến ngày xem loại sp nào bán chạy nhất để có kế hoạch nhập haàng
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
        List<Order> orderList = orderService.findByDate(date);
        List<Product> listAll = new ArrayList<>();
        for(Order order: orderList)
        {
            listAll.addAll(order.getProductList());
        }
        return  listAll;
    }
}
