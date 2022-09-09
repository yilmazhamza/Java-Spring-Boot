package com.example.userproductcomments.repository;

import com.example.userproductcomments.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
