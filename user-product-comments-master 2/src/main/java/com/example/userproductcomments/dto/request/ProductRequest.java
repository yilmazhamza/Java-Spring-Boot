package com.example.userproductcomments.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
@Data
public class ProductRequest {
    @NotBlank
    private String name;
    @Positive
    private double price;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date stk;
}
