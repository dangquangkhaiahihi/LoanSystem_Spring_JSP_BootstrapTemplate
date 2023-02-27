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
import org.springframework.security.core.context.SecurityContextHolder;
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
        userDto.setBalance(userEntity.getBalance());
        return userDto;
    }

    @Override
    public UserDto updateProfile(UserDto userDto) throws Exception {
        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        if (userEntity == null) return null;
        if (StringUtils.isEmpty(userDto.getName()) || StringUtils.isEmpty(userDto.getPhone()) || StringUtils.isEmpty(userDto.getEmail())) {
            throw new Exception("Không được bỏ trống các trường bắt buộc.");
        }else {
            userEntity.setName(userDto.getName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPhone(userDto.getPhone());
            UserEntity userEntity1 = userRepository.save(userEntity);
            BeanUtils.copyProperties(userEntity1, userDto);
            userDto.setBalance(userEntity1.getBalance());
            return userDto;
        }
    }

    @Override
    public void changePass(ChangePassDto changePassDto) throws Exception {
        if(StringUtils.isEmpty(changePassDto.getOldPass()) || StringUtils.isEmpty(changePassDto.getNewPass()) || StringUtils.isEmpty(changePassDto.getReNewPass())){
            throw new Exception("Không được bỏ trống các trường bắt buộc.");
        }

        changePassDto.trimAll();

        if(!changePassDto.getNewPass().equals(changePassDto.getReNewPass())){
            throw new Exception("Xác nhận lại mật khẩu mới không chính xác.");
        }

        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        if(!passwordEncoderUserService().matches(changePassDto.getOldPass(),userEntity.getPassword())){
            throw new Exception("Mật khẩu cũ không chính xác.");
        }

        userEntity.setPassword(passwordEncoderUserService().encode(changePassDto.getNewPass()));
        userRepository.save(userEntity);
    }

    @Override
    public UserDto addBalance(String addBalance) throws Exception {
        if(StringUtils.isEmpty(addBalance)){
            throw new Exception("Không được bỏ trống các trường bắt buộc.");
        }
        addBalance = addBalance.trim();
        if(!StringUtils.containsOnlyNumbers(addBalance)){
            throw new Exception("Nạp tiền cần điền số");
        }

        try{
            Float addBalanceF = Float.parseFloat(addBalance);
            UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
            userEntity.setBalance(userEntity.getBalance() + addBalanceF);
            userRepository.save(userEntity);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity,userDto);
            userDto.setBalance(userEntity.getBalance());
            return userDto;
        }catch (Exception ex){
            throw new Exception("Nạp tiền lỗi, thử lại sau.");
        }
    }
}
