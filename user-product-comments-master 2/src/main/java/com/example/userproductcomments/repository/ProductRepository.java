package com.example.userproductcomments.repository;

import com.example.userproductcomments.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> getAllByStkGreaterThan(Date nowDate);
    List<Product> getAllByStkLessThan(Date nowDate);

}
