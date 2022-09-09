package com.example.userproductcomments.service.Impl;

import com.example.userproductcomments.dto.request.ProductRequest;
import com.example.userproductcomments.dto.response.ProductResponse;
import com.example.userproductcomments.entity.Product;
import com.example.userproductcomments.repository.ProductRepository;
import com.example.userproductcomments.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductResponse save(ProductRequest productRequest) {
        var productEntity = modelMapper.map(productRequest,Product.class);
        return modelMapper.map(productRepository.save(productEntity),ProductResponse.class);
    }

    public List<ProductResponse> getNotExpiredProduct() {

        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();

        return productRepository.getAllByStkGreaterThan(nowDate)
                .stream()
                .map(product -> modelMapper.map(product,ProductResponse.class))
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getExpiredProductAndNullDates() {
        return productRepository.findAll().stream()
                .filter( x -> (Objects.isNull(x.getStk()) || x.getStk().after(new Date())) ? Boolean.TRUE :  Boolean.FALSE)
                .map( x-> modelMapper.map(x,ProductResponse.class))
                .collect(Collectors.toList());
    }
}
