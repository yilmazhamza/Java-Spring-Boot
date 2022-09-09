package com.example.userproductcomments.service;

import com.example.userproductcomments.dto.request.ProductRequest;
import com.example.userproductcomments.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse save(ProductRequest productRequest);

    List<ProductResponse> getNotExpiredProduct();

    List<ProductResponse> getExpiredProductAndNullDates();
}
