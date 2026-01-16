package com.example.freelance_resource_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.TestAccountInfoDto;

@RestController
@RequestMapping("/api/appInfo")
@RequiredArgsConstructor
public class AppInfoController {
	@Value("${app.version}")
	private String version;

	private final Environment env;

	private final TestAccountInfoDto testAccountInfoDto;

	@GetMapping("/version")
	public ResponseEntity<String> getAppVersion() {
		return ResponseEntity.ok(version);
	}

	@GetMapping("/javaVersion")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.ok(env.getProperty("JAVA_HOME"));
	}

	@GetMapping("/testAccount")
	public ResponseEntity<TestAccountInfoDto> getTestAccountInfo() {
		return ResponseEntity.ok(testAccountInfoDto);
	}

}
