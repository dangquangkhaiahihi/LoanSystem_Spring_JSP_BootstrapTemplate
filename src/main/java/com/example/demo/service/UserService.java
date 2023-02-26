package com.example.demo.service;

import com.example.demo.model.UserDto;

public interface UserService {
    UserDto getUserDetailByUsername(String username);

    UserDto updateProfile(UserDto userDto);
}
