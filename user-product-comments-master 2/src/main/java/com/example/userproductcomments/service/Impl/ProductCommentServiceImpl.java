package com.example.userproductcomments.service.Impl;

import com.example.userproductcomments.dto.request.ProductCommentRequest;
import com.example.userproductcomments.dto.response.CommentsResponse;
import com.example.userproductcomments.dto.response.ProductCommentResponse;
import com.example.userproductcomments.entity.ProductComment;
import com.example.userproductcomments.repository.ProductCommentRepository;
import com.example.userproductcomments.repository.ProductRepository;
import com.example.userproductcomments.repository.UserRepository;
import com.example.userproductcomments.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public ProductCommentResponse save(ProductCommentRequest productCommentRequest) {

        var productInDatabase= productRepository.findById(productCommentRequest.getProduct_id())
                .orElseThrow(()->new NoSuchElementException("No products found for this id"));
        var userInDatabase= userRepository.findById(productCommentRequest.getUser_id())
                .orElseThrow(() -> new NoSuchElementException("No users found for this id"));

        ProductComment productCommentEntity = new ProductComment();
        productCommentEntity.setProduct(productInDatabase);
        productCommentEntity.setUser(userInDatabase);
        productCommentEntity.setComment(productCommentRequest.getComment());
        productCommentEntity.setCommentDate(productCommentRequest.getCommentDate());

        productCommentRepository.save(productCommentEntity);

        ProductCommentResponse productCommentResponse = new ProductCommentResponse();
        productCommentResponse.setComment(productCommentRequest.getComment());

        return productCommentResponse;
    }

    public List<CommentsResponse> getCommentsToProduct(long id) {
        var productInDatabase = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No product found for this id"));
        var listOfComments =productInDatabase.getProductComment();
        return listOfComments.stream().map(productComment -> modelMapper.map(productComment,CommentsResponse.class)).collect(Collectors.toList());

    }

    public List<CommentsResponse> getCommentsToUser(long id) {
        var userInDatabase = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No user found for this id"));
        var listOfComments = userInDatabase.getProductComments();
        return listOfComments.stream().map(productComment -> modelMapper.map(productComment,CommentsResponse.class)).collect(Collectors.toList());
    }

    public List<CommentsResponse> getCommentsBetweenCertainDatesForProduct(long id, String startDate, String endDate) {
        var productInDatabase = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No product found for this id"));

        var listOfComments= productInDatabase.getProductComment();
         return listOfComments
                .stream()
                .filter(productComment -> isBetweenDates(startDate, endDate, productComment))
                 .map(productComment -> modelMapper.map(productComment,CommentsResponse.class))
                .collect(Collectors.toList());

    }

    private boolean isBetweenDates(String startDate, String endDate, ProductComment productComment) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newStartDate ;
        Date newEndDate ;
        try {
            newStartDate = dateFormat.parse(startDate);
            newEndDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return productComment.getCommentDate().after(newStartDate) && productComment.getCommentDate().before(newEndDate);
    }

    public List<CommentsResponse> getCommentsBetweenCertainDatesForUser(long id, String startDate, String endDate) {
        var userInDatabase = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("No user found for this id"));

        return userInDatabase.getProductComments()
                .stream()
                .filter(productComment -> isBetweenDates(startDate,endDate,productComment))
                .map(productComment -> modelMapper.map(productComment,CommentsResponse.class))
                .collect(Collectors.toList());
    }
}
