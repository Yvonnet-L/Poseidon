package com.ly.p7.Poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PoseidonApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 String pw = encoder.encode("123");
		 System.out.println("[ "+ pw + " ]");

		SpringApplication.run(PoseidonApplication.class, args);
	}

}
