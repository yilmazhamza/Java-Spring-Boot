package com.example.userproductcomments.controller;

import com.example.userproductcomments.dto.request.ProductCommentRequest;
import com.example.userproductcomments.dto.response.CommentsResponse;
import com.example.userproductcomments.dto.response.ProductCommentResponse;
import com.example.userproductcomments.service.Impl.ProductCommentServiceImpl;
import com.example.userproductcomments.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productComment")
@RequiredArgsConstructor
@Validated
public class ProductCommentController {

    private final ProductCommentService productCommentService;

    @PostMapping("/addProductComment")
    public ProductCommentResponse addProductComment (@RequestBody @Validated ProductCommentRequest productCommentRequest){
            return productCommentService.save(productCommentRequest);
    }
    @GetMapping("/getCommentsToProduct")
    public List<CommentsResponse> getCommentsToProduct(@RequestParam long id){
        return productCommentService.getCommentsToProduct(id);
    }
    @GetMapping("/getCommentsToUser")
    public List<CommentsResponse> getCommentsToUser(@RequestParam long id){
        return productCommentService.getCommentsToUser(id);
    }
    @GetMapping("/getCommentsBetweenCertainDatesForProduct")
    public List<CommentsResponse> getCommentsBetweenCertainDatesForProduct(@RequestParam long id, @RequestParam String startDate, @RequestParam String endDate){
        return productCommentService.getCommentsBetweenCertainDatesForProduct(id,startDate,endDate);
    }
    @GetMapping("/getCommentsBetweenCertainDatesForUser")
    public List<CommentsResponse> getCommentsBetweenCertainDatesForUser(@RequestParam long id , @RequestParam String startDate,@RequestParam String endDate){
        return productCommentService.getCommentsBetweenCertainDatesForUser(id,startDate,endDate);
    }


}
