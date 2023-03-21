package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.ChangePassDto;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoderUserService() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDto getUserDetailByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) return new UserDto();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

    @Override
    public UserDto updateProfile(UserDto userDto) throws Exception {
        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        if (userEntity == null) return null;
        if (StringUtils.isEmpty(userDto.getName()) || StringUtils.isEmpty(userDto.getEmail())) {
            throw new Exception("Không được bỏ trống các trường bắt buộc.");
        } else {
            userEntity.setName(userDto.getName());
            userEntity.setEmail(userDto.getEmail());
            userRepository.updateProfile(userEntity.getName(), userEntity.getEmail(), userEntity.getId());
            BeanUtils.copyProperties(userEntity, userDto);
            return userDto;
        }
    }

    @Override
    public void changePass(ChangePassDto changePassDto) throws Exception {
        if (StringUtils.isEmpty(changePassDto.getOldPass()) || StringUtils.isEmpty(changePassDto.getNewPass()) || StringUtils.isEmpty(changePassDto.getReNewPass())) {
            throw new Exception("Không được bỏ trống các trường bắt buộc.");
        }

        changePassDto.trimAll();

        if (!changePassDto.getNewPass().equals(changePassDto.getReNewPass())) {
            throw new Exception("Xác nhận lại mật khẩu mới không chính xác.");
        }

        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        if (!passwordEncoderUserService().matches(changePassDto.getOldPass(), userEntity.getPassword())) {
            throw new Exception("Mật khẩu cũ không chính xác.");
        }

        userEntity.setPassword(passwordEncoderUserService().encode(changePassDto.getNewPass()));
        userRepository.updatePassword(userEntity.getPassword(), userEntity.getId());
    }

    @Override
    public void register(UserDto userDto) throws Exception {
        UserEntity checkUsername = userRepository.findByUsername(userDto.getUsername());
        UserEntity checkEmail = userRepository.findByEmail(userDto.getEmail());

        if (checkUsername != null) {
            throw new Exception("Tên đăng nhập đã tồn tại.");
        }
        if (checkEmail != null) {
            throw new Exception("Email đã tồn tại.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setName(userDto.getName());

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoderUserService().encode(userDto.getPassword()));
        userRepository.save(userEntity.getName(), userEntity.getEmail(),
                userEntity.getUsername(), userEntity.getPassword(), 0L);
    }
}
