package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.InstructorDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface InstructorService {
    ApiResponse<InstructorDTO> addInstructor(InstructorDTO instructorDTO);
    ApiResponse<InstructorDTO> getInstructor(Long id);
    ApiResponse<Boolean> deleteInstructor(Long id);
    ApiResponse<Boolean> enrollCourse(Long instructorId, Long courseId);
    ApiResponse<Boolean> withdrawCourse(Long instructorId, Long courseId);
    ApiResponse<Boolean> updateCourseStatus(Long studentId, Long courseId, String status);
}
