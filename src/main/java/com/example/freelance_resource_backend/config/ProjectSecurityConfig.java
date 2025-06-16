package com.example.freelance_resource_backend.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.example.freelance_resource_backend.entryPoint.CustomBasicAuthenticationEntryPoint;
import com.example.freelance_resource_backend.filter.CsrfCookieFilter;
import com.example.freelance_resource_backend.filter.JwtTokenValidationFilter;
import com.example.freelance_resource_backend.handler.CustomAuthenticationFailureHandler;
import com.example.freelance_resource_backend.handler.CustomAuthenticationSuccessHandler;
import com.example.freelance_resource_backend.provider.EmailPasswordRoleAuthenticationProvider;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;

@Configuration
public class ProjectSecurityConfig {
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
		http
				.securityContext(contextConfig -> contextConfig.requireExplicitSave(false))
				.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setMaxAge(3600L);
						return config;
					}
				}))
				.csrf(csrfConfig -> csrfConfig
						.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
						.ignoringRequestMatchers(
								"/checkLogin",
								"/apiLogin",
								"/apiLogout",
								"/user/createUser",
								"/forgetPassword",
								"/error"
						)
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
				.authorizeHttpRequests((requests) -> requests
				.requestMatchers(
						"/testLogin",
						"/subscription",
						"/subscription/*",
						"/announcement/*",
						"/subscription/unsubscribed/*",
						"/subject/*"
				).authenticated()
				.requestMatchers(
						"/checkLogin",
						"/apiLogin",
						"/apiLogout",
						"/user/createUser",
						"/forgetPassword",
						"/error"
				).permitAll()
				.anyRequest().authenticated()
		);
		http.formLogin(flc -> flc
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler)
		);
		http.logout(loc -> loc
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("loginToken")
				.deleteCookies("JSESSIONID")
		);
		http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
		http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public AuthenticationManager authenticationManager(FreelanceUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		EmailPasswordRoleAuthenticationProvider provider = new EmailPasswordRoleAuthenticationProvider(userDetailsService, passwordEncoder);
		ProviderManager providerManager = new ProviderManager(provider);
		providerManager.setEraseCredentialsAfterAuthentication(false);
		return providerManager;
	}
}
