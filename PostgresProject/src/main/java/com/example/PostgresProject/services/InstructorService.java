package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.InstructorDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface InstructorService {
    ResponseEntity<ApiResponse<InstructorDTO>> addInstructor(InstructorDTO instructorDTO);
    ResponseEntity<ApiResponse<InstructorDTO>> getInstructor(Long id);
    ResponseEntity<ApiResponse<Boolean>> deleteInstructor(Long id);
    ResponseEntity<ApiResponse<Boolean>> enrollCourse(Long instructorId, Long courseId);
    ResponseEntity<ApiResponse<Boolean>> withdrawCourse(Long instructorId, Long courseId);
    ResponseEntity<ApiResponse<Boolean>> updateCourseStatus(Long studentId, Long courseId, String status);
}
