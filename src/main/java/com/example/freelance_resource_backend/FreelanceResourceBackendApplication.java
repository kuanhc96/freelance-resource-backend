package com.example.freelance_resource_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug=true)
public class FreelanceResourceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceResourceBackendApplication.class, args);
	}

}
