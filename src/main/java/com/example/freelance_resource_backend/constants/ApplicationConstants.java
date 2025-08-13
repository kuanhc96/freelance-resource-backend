package com.example.freelance_resource_backend.constants;

public class ApplicationConstants {

	// account level security
	public static final String ROLE_STUDENT = "hasRole('ROLE_STUDENT')";
	public static final String ROLE_INSTRUCTOR = "hasRole('ROLE_INSTRUCTOR')";

	// client level security
	public static final String ROLE_INTEGRATION_TEST = "hasRole('ROLE_INTEGRATION_TEST')";
	public static final String ROLE_FREELANCE_FE = "hasRole('ROLE_FREELANCE_FE')";

	public static final String INSTRUCTOR = "(" + ROLE_INSTRUCTOR + " and " + ROLE_FREELANCE_FE + ") or " + ROLE_INTEGRATION_TEST;
	public static final String STUDENT = "(" + ROLE_STUDENT + " and " + ROLE_FREELANCE_FE + ") or " + ROLE_INTEGRATION_TEST;
	public static final String INSTRUCTOR_OR_STUDENT = ROLE_INSTRUCTOR + " or " + ROLE_STUDENT + " or " + ROLE_INTEGRATION_TEST;
}
