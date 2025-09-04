package com.example.freelance_resource_backend.constants;

public class ApplicationConstants {

	// account level security
	public static final String ROLE_STUDENT = "hasRole('ROLE_STUDENT')";
	public static final String ROLE_INSTRUCTOR = "hasRole('ROLE_INSTRUCTOR')";

	// client level security
	public static final String INTEGRATION_TEST = "hasAuthority('INTEGRATION_TEST')";
	public static final String FREELANCE_FE = "hasAuthority('FREELANCE_FE')";

	public static final String INSTRUCTOR = "(" + ROLE_INSTRUCTOR + " and " + FREELANCE_FE + ") or " + INTEGRATION_TEST;
	public static final String STUDENT = "(" + ROLE_STUDENT + " and " + FREELANCE_FE + ") or " + INTEGRATION_TEST;
	public static final String INSTRUCTOR_OR_STUDENT = ROLE_INSTRUCTOR + " or " + ROLE_STUDENT + " or " + INTEGRATION_TEST;
}
