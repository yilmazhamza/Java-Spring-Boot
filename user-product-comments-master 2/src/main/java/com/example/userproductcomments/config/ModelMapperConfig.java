package com.example.userproductcomments.config;

import com.example.userproductcomments.dto.request.ProductCommentRequest;
import com.example.userproductcomments.dto.request.ProductRequest;
import com.example.userproductcomments.dto.request.UserRequest;
import com.example.userproductcomments.dto.response.CommentsResponse;
import com.example.userproductcomments.dto.response.ProductResponse;
import com.example.userproductcomments.dto.response.UserResponse;
import com.example.userproductcomments.entity.Product;
import com.example.userproductcomments.entity.ProductComment;
import com.example.userproductcomments.entity.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ModelMapperConfig {
    private static final Converter<UserRequest, User> USER_REQUEST_USER_CONVERTER = mappingContext -> {
        var user = new User();
        var userRequest = mappingContext.getSource();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        return user;
    };

    private static final Converter<User, UserResponse> USER_USER_RESPONSE_CONVERTER = mappingContext -> {
        var userResponse = new UserResponse();
        var user = mappingContext.getSource();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());

        return userResponse;
    };

    private static final Converter<ProductRequest, Product> PRODUCT_REQUEST_PRODUCT_CONVERTER = mappingContext -> {
        var product = new Product();
        var productRequest = mappingContext.getSource();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStk(productRequest.getStk());

        return product;
    };

    private static final Converter<Product, ProductResponse> PRODUCT_PRODUCT_RESPONSE_CONVERTER = mappingContext -> {
        var productResponse = new ProductResponse();
        var product = mappingContext.getSource();
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        if(Objects.nonNull(product.getStk()))
            productResponse.setStk(product.getStk());
        return productResponse;
    };

    private static final Converter<ProductComment, CommentsResponse> PRODUCT_COMMENT_COMMENTS_RESPONSE_CONVERTER = mappingContext -> {
        var commentsResponse = new CommentsResponse();
        var productComment = mappingContext.getSource();
        commentsResponse.setComments(productComment.getComment());

        return commentsResponse;
    };

    @Bean
    ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(USER_REQUEST_USER_CONVERTER,UserRequest.class,User.class);
        modelMapper.addConverter(USER_USER_RESPONSE_CONVERTER,User.class,UserResponse.class);
        modelMapper.addConverter(PRODUCT_REQUEST_PRODUCT_CONVERTER,ProductRequest.class,Product.class);
        modelMapper.addConverter(PRODUCT_PRODUCT_RESPONSE_CONVERTER,Product.class,ProductResponse.class);
        modelMapper.addConverter(PRODUCT_COMMENT_COMMENTS_RESPONSE_CONVERTER,ProductComment.class,CommentsResponse.class);
        return modelMapper;
    }
}
