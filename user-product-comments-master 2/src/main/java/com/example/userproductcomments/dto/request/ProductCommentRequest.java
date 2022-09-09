package com.example.userproductcomments.dto.request;

import com.example.userproductcomments.entity.Product;
import com.example.userproductcomments.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
@Data
public class ProductCommentRequest {
    @NotBlank
    private String comment;
    @NotNull
    private Date commentDate;
    @NotBlank
    private long product_id;
    @NotBlank
    private long user_id;
}
