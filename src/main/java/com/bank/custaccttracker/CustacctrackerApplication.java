package com.bank.custaccttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class CustacctrackerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CustacctrackerApplication.class, args);
	}

}
