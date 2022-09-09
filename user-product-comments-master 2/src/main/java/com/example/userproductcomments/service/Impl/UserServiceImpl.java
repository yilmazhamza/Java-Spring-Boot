package com.example.userproductcomments.service.Impl;

import com.example.userproductcomments.dto.request.UserRequest;
import com.example.userproductcomments.dto.response.UserResponse;
import com.example.userproductcomments.entity.User;
import com.example.userproductcomments.repository.UserRepository;
import com.example.userproductcomments.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponse save(UserRequest userRequest) {
        var userEntity = modelMapper.map(userRequest,User.class);
        return modelMapper.map(userRepository.save(userEntity),UserResponse.class);
    }

    @Override
    public List<UserResponse> getUser() {
        var listOfUsers = userRepository.findAll();
        return listOfUsers.stream().map(user -> modelMapper.map(user,UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse getById(long id) {
        var userInDatabase = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No users found for this id"));
        return modelMapper.map(userInDatabase,UserResponse.class);
    }
}
