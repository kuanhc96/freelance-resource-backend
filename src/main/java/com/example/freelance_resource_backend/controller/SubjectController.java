package com.example.freelance_resource_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.SubjectService;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
	private final SubjectService subjectService;

	@PostMapping("/createSubject")
	public CreateSubjectResponse createSubject(@RequestBody CreateSubjectRequest request) {
		subjectService.createSubject(request.getSubjectName(), request.getInstructorGUID(), request.getPrice());
		return CreateSubjectResponse.builder()
				.subjectName(request.getSubjectName())
				.instructorGUID(request.getInstructorGUID())
				.price(request.getPrice())
				.build();
	}

	@GetMapping("/{instructorGUID}")
	public ResponseEntity<List<GetSubjectResponse>> getSubjectsByInstructor(@PathVariable String instructorGUID) throws ResourceNotFoundException {
		List<GetSubjectResponse> subjects = subjectService.getSubjectsByInstructorGUID(instructorGUID);
		if (subjects.isEmpty()) {
			throw new ResourceNotFoundException("No subjects found for instructor with GUID: " + instructorGUID);
		}
		return ResponseEntity.ok(subjects);

	}

	@PutMapping("/updateSubjectName")
	public UpdateSubjectNameResponse updateSubjectName(@RequestBody UpdateSubjectNameRequest updateSubjectRequest) throws ResourceNotFoundException {
		subjectService.updateSubjectName(updateSubjectRequest.getSubjectName(), updateSubjectRequest.getInstructorGUID(), updateSubjectRequest.getNewSubjectName());
		return UpdateSubjectNameResponse.builder()
				.newSubjectName(updateSubjectRequest.getNewSubjectName())
				.instructorGUID(updateSubjectRequest.getInstructorGUID())
				.build();
	}

	@PutMapping("/updateSubjectPrice")
	public UpdateSubjectPriceResponse updateSubjectPrice(@RequestBody UpdateSubjectPriceRequest updateSubjectRequest) throws ResourceNotFoundException {
		subjectService.updateSubjectPrice(updateSubjectRequest.getSubjectName(), updateSubjectRequest.getInstructorGUID(), updateSubjectRequest.getPrice());
		return UpdateSubjectPriceResponse.builder()
				.subjectName(updateSubjectRequest.getSubjectName())
				.instructorGUID(updateSubjectRequest.getInstructorGUID())
				.price(updateSubjectRequest.getPrice())
				.build();
	}

}
