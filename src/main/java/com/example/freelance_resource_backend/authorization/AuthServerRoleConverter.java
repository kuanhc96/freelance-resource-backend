package com.example.freelance_resource_backend.authorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public class AuthServerRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
		ArrayList<String> scopes = (ArrayList<String>) source.getClaims().get("scope");
		if (scopes == null || scopes.isEmpty()) {
			return new ArrayList<>();
		}
		Collection<GrantedAuthority> grantedAuthorities = scopes.stream()
				.map(scope -> "ROLE_" + scope) // Prefixing with "ROLE_"
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		ArrayList<String> roles = (ArrayList<String>) source.getClaims().get("roles");
		if (roles == null || roles.isEmpty()) {
			return grantedAuthorities;
		}
		grantedAuthorities.addAll(roles.stream()
				.map(SimpleGrantedAuthority::new)
				.toList());
		return grantedAuthorities;
	}
}
