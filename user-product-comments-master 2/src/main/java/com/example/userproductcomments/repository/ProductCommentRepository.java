package com.example.userproductcomments.repository;

import com.example.userproductcomments.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Long> {


}
