package com.example.freelance_resource_backend.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;

import com.example.freelance_resource_backend.authentication.EmailPasswordRoleAuthenticationToken;
import com.example.freelance_resource_backend.constants.ApplicationConstants;
import com.example.freelance_resource_backend.enums.UserRole;

public class JwtTokenValidationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String jwt = Arrays.stream(request.getCookies())
				.filter(cookie -> "loginToken".equals(cookie.getName()))
				.map(Cookie::getValue)
				.findFirst()
				.orElse(null);
		if (null != jwt) {
			// validate token
			try {
				Environment env = getEnvironment();
				if (env != null) {
					String jwtSecret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
					SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
					if (null != secretKey) {
						// validate token
						Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
						String email = String.valueOf(claims.get("email", String.class));
						String userGUID = String.valueOf(claims.get("userGUID", String.class));
						UserRole role = UserRole.valueOf(String.valueOf(claims.get("role", String.class)));
						String authorities = String.valueOf(claims.get("authorities", String.class));
						Authentication authentication = new EmailPasswordRoleAuthenticationToken(userGUID, email, role,
								null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}

			} catch (Exception e) {
				throw new BadCredentialsException("Invalid token");
			}
		}
		// Continue the filter chain
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// Define the conditions under which this filter should not be applied
		// For example, you can skip certain paths or methods
		String path = request.getRequestURI();
		return path.equals("/apiLogin") || path.equals("/user/createUser") || path.equals("/forgetPassword");
	}
}
