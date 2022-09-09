package com.example.userproductcomments.controller;

import com.example.userproductcomments.dto.request.UserRequest;
import com.example.userproductcomments.dto.response.UserResponse;
import com.example.userproductcomments.service.Impl.UserServiceImpl;
import com.example.userproductcomments.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public UserResponse addUser (@RequestBody @Validated UserRequest userRequest){
        return userService.save(userRequest);
    }
    @GetMapping("/getUsers")
    public List<UserResponse> getUser(){
        return userService.getUser();
    }

    @GetMapping("/getById")
    public UserResponse getById (@RequestParam long id){
        return userService.getById(id);
    }
}
