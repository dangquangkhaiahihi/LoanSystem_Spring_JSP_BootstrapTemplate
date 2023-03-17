package com.example.demo;

import com.example.demo.common.Constant;
import com.example.demo.common.Utils;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class LoanSystemApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args)  {
		SpringApplication.run(LoanSystemApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder123() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("dang.quang.khai2610@gmail.com");
		userEntity.setName("Đặng Quang Khải");

		userEntity.setUsername("user00");
		userEntity.setPassword(passwordEncoder123().encode("1"));
		userRepository.save(userEntity);



	}

}
