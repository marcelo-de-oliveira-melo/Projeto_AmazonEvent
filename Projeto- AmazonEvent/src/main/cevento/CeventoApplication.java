package com.spring.cevento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})

public class CeventoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeventoApplication.class, args);
		System.out.print(" Senha: ");
		System.out.print(new BCryptPasswordEncoder().encode("amazon"));
		System.out.print(" ||fim ");
	}


}
