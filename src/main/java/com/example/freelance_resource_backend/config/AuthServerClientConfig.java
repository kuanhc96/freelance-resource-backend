package com.example.freelance_resource_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

import com.example.freelance_resource_backend.client.AuthServerClient;

@Configuration
public class AuthServerClientConfig {
	@Bean
	public Encoder feignFormEncoder() {
		return new SpringFormEncoder();
	}
}
