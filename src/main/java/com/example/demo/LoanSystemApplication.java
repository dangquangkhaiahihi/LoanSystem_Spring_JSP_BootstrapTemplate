package com.example.demo;

import com.example.demo.common.Constant;
import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@ServletComponentScan
public class LoanSystemApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

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


		PersonEntity person0 = new PersonEntity();
		person0.setName("ahihi");
		person0.setAddress("ahihi");
		person0.setPhone("0123456789");
		person0.setEmail("0123456789@gmail.com");
		person0.setTotalAmount(1000000L);
		person0.setUser(userEntity);
		person0.setCreatedDate(Instant.now().minus(7, ChronoUnit.DAYS));
		person0.setLastModifiedDate(Instant.now());
		personRepository.save(person0);
	}

}
