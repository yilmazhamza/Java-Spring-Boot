package com.example.userproductcomments.service;

import com.example.userproductcomments.dto.request.UserRequest;
import com.example.userproductcomments.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest userRequest);

    List<UserResponse> getUser();

    UserResponse getById(long id);
}
