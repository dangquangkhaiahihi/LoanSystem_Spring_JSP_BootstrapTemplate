package com.example.demo.service;

import com.example.demo.entity.PersonEntity;
import com.example.demo.service.dto.ChangePassDto;
import com.example.demo.service.dto.UserDto;

public interface UserService {
    UserDto getUserDetailByUsername(String username);

    UserDto updateProfile(UserDto userDto) throws Exception;

    void changePass(ChangePassDto changePassDto) throws Exception;

    void register(UserDto userDto) throws Exception;

    void updateAmount(PersonEntity personEntity, Long amount, Boolean isPlus);
}
