package com.example.demo;

import com.example.demo.common.Constant;
import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TicketRepository;
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

	@Autowired
	TicketRepository ticketRepository;

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
		userEntity.setEmail("random-mail@gmail.com");
		userEntity.setName("Random Name");

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

		TicketEntity ticket00 = new TicketEntity();
		ticket00.setAmount(3000000L);
		ticket00.setIsPlus(true);
		ticket00.setNote("Vay 3tr");
		ticket00.setDateOfTrans(Instant.now().minus(1,ChronoUnit.DAYS));
		ticket00.setLastModifiedDate(Instant.now().minus(1,ChronoUnit.DAYS));
		ticket00.setPerson(person0);
		ticketRepository.save(ticket00);

		TicketEntity ticket01 = new TicketEntity();
		ticket01.setAmount(2000000L);
		ticket01.setIsPlus(false);
		ticket01.setNote("Trả nợ 2tr");
		ticket01.setDateOfTrans(Instant.now());
		ticket01.setLastModifiedDate(Instant.now());
		ticket01.setPerson(person0);
		ticketRepository.save(ticket01);

		PersonEntity person1 = new PersonEntity();
		person1.setName("gói bim bim");
		person1.setAddress("ahihi");
		person1.setPhone("0159357456");
		person1.setEmail("snack@gmail.com");
		person1.setTotalAmount(40000L);
		person1.setUser(userEntity);
		person1.setCreatedDate(Instant.now().minus(5, ChronoUnit.DAYS));
		person1.setLastModifiedDate(Instant.now());
		personRepository.save(person1);

		TicketEntity ticket10 = new TicketEntity();
		ticket10.setAmount(100000L);
		ticket10.setIsPlus(true);
		ticket10.setNote("Vay 100k");
		ticket10.setDateOfTrans(Instant.now().minus(1,ChronoUnit.DAYS));
		ticket10.setLastModifiedDate(Instant.now().minus(1,ChronoUnit.DAYS));
		ticket10.setPerson(person1);
		ticketRepository.save(ticket10);

		TicketEntity ticket11 = new TicketEntity();
		ticket11.setAmount(60000L);
		ticket11.setIsPlus(false);
		ticket11.setNote("Trả nợ 60k");
		ticket11.setDateOfTrans(Instant.now());
		ticket11.setLastModifiedDate(Instant.now());
		ticket11.setPerson(person1);
		ticketRepository.save(ticket11);
	}

}
