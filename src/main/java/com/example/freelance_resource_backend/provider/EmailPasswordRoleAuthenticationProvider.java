package com.example.freelance_resource_backend.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import com.example.freelance_resource_backend.authentication.EmailPasswordRoleAuthenticationToken;
import com.example.freelance_resource_backend.dto.FreelanceAppUserDetails;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;

@Component
@AllArgsConstructor
public class EmailPasswordRoleAuthenticationProvider implements AuthenticationProvider {

	private FreelanceUserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserRole role = ((EmailPasswordRoleAuthenticationToken) authentication).getRole();
		FreelanceAppUserDetails userDetails = (FreelanceAppUserDetails) userDetailsService.loadUserByEmailAndRole(email, role);
		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			return new EmailPasswordRoleAuthenticationToken(
					userDetails.getUserGUID(), email, userDetails.getRole(), password, userDetails.getAuthorities()
			);
		} else {
			throw new BadCredentialsException("Invalid password!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (EmailPasswordRoleAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
