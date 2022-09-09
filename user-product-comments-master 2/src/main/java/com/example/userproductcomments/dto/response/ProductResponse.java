package com.example.userproductcomments.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ProductResponse {
    private String name;
    private double price;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date stk;
}
