package com.example.userproductcomments.controller;

import com.example.userproductcomments.dto.request.ProductRequest;
import com.example.userproductcomments.dto.response.ProductResponse;
import com.example.userproductcomments.service.Impl.ProductServiceImpl;
import com.example.userproductcomments.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ProductResponse addUser(@RequestBody  @Validated ProductRequest productRequest){
        return productService.save(productRequest);
    }

    @GetMapping("/getNotExpiredProduct")
    public List<ProductResponse> getNotExpiredProduct(){
        return productService.getNotExpiredProduct();
    }

    @GetMapping("/getExpiredProduct")
    public List<ProductResponse> getExpiredProduct(){
        return productService.getExpiredProductAndNullDates();
    }
}
