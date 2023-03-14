package com.example.demo.service;

import com.example.demo.model.ChangePassDto;
import com.example.demo.model.UserDto;

public interface UserService {
    UserDto getUserDetailByUsername(String username);

    UserDto updateProfile(UserDto userDto) throws Exception;

    void changePass(ChangePassDto changePassDto) throws Exception;

    UserDto addBalance(String addBalance) throws Exception;

    void register(UserDto userDto) throws Exception;
}
