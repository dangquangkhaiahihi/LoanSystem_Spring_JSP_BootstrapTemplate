package com.example.demo;

import com.example.demo.common.Constant;
import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class LoanSystemApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoanRepository loanRepository;

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
		userEntity.setGender(true);
		userEntity.setPhone("0559261020");
		userEntity.setCccdNum("001200013959");

		userEntity.setUsername("user00");
		userEntity.setPassword(passwordEncoder123().encode("1"));
		userRepository.save(userEntity);

		LoanEntity loanEntity = new LoanEntity();
		loanEntity.setAmount(100000f);
		loanEntity.setCreatedAt(LocalDateTime.now());
		loanEntity.setDeadline(loanEntity.getCreatedAt().plus(1, ChronoUnit.MONTHS));
		loanEntity.setType(Constant.LOAN_TYPE_ONE_TIME);
		loanEntity.setStatus(true);
		loanEntity.setUser(userEntity);
		loanRepository.save(loanEntity);

		LoanEntity loanEntity1 = new LoanEntity();
		loanEntity1.setAmount(10000000f);
		loanEntity1.setCreatedAt(Utils.convertyyyyMMddToLocalDateTime("2022-12-29"));
		loanEntity1.setDeadline(loanEntity1.getCreatedAt().plus(12, ChronoUnit.MONTHS));
		loanEntity1.setType(Constant.LOAN_TYPE_INSTALLMENT);
		loanEntity1.setStatus(false);
		loanEntity1.setUser(userEntity);
		loanRepository.save(loanEntity1);

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setEmail("somerandomemail@gmail.com");
		userEntity2.setName("Săm ran đầm name");
		userEntity2.setGender(true);
		userEntity2.setPhone("0559310270");
		userEntity2.setCccdNum("001208139509");

		userEntity2.setUsername("user01");
		userEntity2.setPassword(passwordEncoder123().encode("1"));
		userRepository.save(userEntity2);

		LoanEntity loanEntity2 = new LoanEntity();
		loanEntity2.setAmount(5000000f);
		loanEntity2.setCreatedAt(LocalDateTime.now());
		loanEntity2.setDeadline(loanEntity2.getCreatedAt().plus(12, ChronoUnit.MONTHS));
		loanEntity2.setType(Constant.LOAN_TYPE_INSTALLMENT);
		loanEntity2.setStatus(false);
		loanEntity2.setUser(userEntity2);
		loanRepository.save(loanEntity2);
	}

}