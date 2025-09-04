package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.subject.CreateSubjectRequest;
import com.example.freelance_resource_backend.dto.request.subject.UpdateSubjectNameRequest;
import com.example.freelance_resource_backend.dto.request.subject.UpdateSubjectPriceRequest;
import com.example.freelance_resource_backend.dto.response.subject.GetSubjectResponse;
import com.example.freelance_resource_backend.dto.response.subject.CreateSubjectResponse;
import com.example.freelance_resource_backend.dto.response.subject.UpdateSubjectNameResponse;
import com.example.freelance_resource_backend.dto.response.subject.UpdateSubjectPriceResponse;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.SubjectService;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
	private final SubjectService subjectService;

	@PostMapping("/createSubject")
	@PreAuthorize(INSTRUCTOR)
	public CreateSubjectResponse createSubject(@RequestBody CreateSubjectRequest request) {
		SubjectEntity subjectEntity = subjectService.createSubject(
				request.getSubjectName(),
				request.getInstructorGUID(),
				request.getPrice(),
				request.getDuration(),
				request.getSubjectDescription()
		);
		return CreateSubjectResponse.builder()
				.subjectGUID(subjectEntity.getSubjectGUID())
				.subjectName(request.getSubjectName())
				.instructorGUID(request.getInstructorGUID())
				.price(request.getPrice())
				.build();
	}

	@GetMapping("/{instructorGUID}")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<List<GetSubjectResponse>> getSubjectsByInstructor(@PathVariable String instructorGUID) throws ResourceNotFoundException {
		List<GetSubjectResponse> subjects = subjectService.getSubjectsByInstructorGUID(instructorGUID);
		if (subjects.isEmpty()) {
			throw new ResourceNotFoundException("No subjects found for instructor with GUID: " + instructorGUID);
		}
		return ResponseEntity.ok(subjects);

	}

	@PutMapping("/updateSubjectName")
	@PreAuthorize(INSTRUCTOR)
	public UpdateSubjectNameResponse updateSubjectName(@RequestBody UpdateSubjectNameRequest updateSubjectRequest) throws ResourceNotFoundException {
		subjectService.updateSubjectName(updateSubjectRequest.getSubjectName(), updateSubjectRequest.getInstructorGUID(), updateSubjectRequest.getNewSubjectName());
		return UpdateSubjectNameResponse.builder()
				.newSubjectName(updateSubjectRequest.getNewSubjectName())
				.instructorGUID(updateSubjectRequest.getInstructorGUID())
				.build();
	}

	@PutMapping("/updateSubjectPrice")
	@PreAuthorize(INSTRUCTOR)
	public UpdateSubjectPriceResponse updateSubjectPrice(@RequestBody UpdateSubjectPriceRequest updateSubjectRequest) throws ResourceNotFoundException {
		subjectService.updateSubjectPrice(updateSubjectRequest.getSubjectName(), updateSubjectRequest.getInstructorGUID(), updateSubjectRequest.getPrice());
		return UpdateSubjectPriceResponse.builder()
				.subjectName(updateSubjectRequest.getSubjectName())
				.instructorGUID(updateSubjectRequest.getInstructorGUID())
				.price(updateSubjectRequest.getPrice())
				.build();
	}

}
