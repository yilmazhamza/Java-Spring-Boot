package com.example.userproductcomments.service;

import com.example.userproductcomments.dto.request.ProductCommentRequest;
import com.example.userproductcomments.dto.response.CommentsResponse;
import com.example.userproductcomments.dto.response.ProductCommentResponse;

import java.util.List;

public interface ProductCommentService {
    ProductCommentResponse save(ProductCommentRequest productCommentRequest);

    List<CommentsResponse> getCommentsToProduct(long id);

    List<CommentsResponse> getCommentsToUser(long id);

    List<CommentsResponse> getCommentsBetweenCertainDatesForProduct(long id, String startDate, String endDate);

    List<CommentsResponse> getCommentsBetweenCertainDatesForUser(long id, String startDate, String endDate);
}
