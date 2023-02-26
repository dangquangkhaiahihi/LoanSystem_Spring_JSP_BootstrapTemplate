package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUserDetailByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) return new UserDto();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userEntity == null) return null;
        if (userDto.getName() != null && userDto.getPhone() != null && userDto.getEmail() != null) {
            userEntity.setName(userDto.getName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPhone(userDto.getPhone());
            UserEntity userEntity1 = userRepository.save(userEntity);
            BeanUtils.copyProperties(userEntity1, userDto);
            return userDto;
        }else {
            return null;
        }
    }
}
