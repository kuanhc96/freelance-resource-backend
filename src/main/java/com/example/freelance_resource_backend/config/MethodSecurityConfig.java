package com.example.freelance_resource_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@Profile("!dev")
@EnableMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
}
