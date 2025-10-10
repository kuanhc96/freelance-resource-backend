package com.example.freelance_resource_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.freelance_resource_backend.dto.TestAccountInfoDto;

@SpringBootApplication
@EnableWebSecurity(debug=true)
@EnableConfigurationProperties(value={TestAccountInfoDto.class})
@EnableFeignClients
public class FreelanceResourceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceResourceBackendApplication.class, args);
	}

}
