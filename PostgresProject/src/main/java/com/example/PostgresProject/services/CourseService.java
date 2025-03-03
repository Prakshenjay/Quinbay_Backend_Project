package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.CourseDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    ResponseEntity<ApiResponse<CourseDTO>> addCourse(CourseDTO courseDTO);
    ResponseEntity<ApiResponse<CourseDTO>> getCourse(Long id);
    ResponseEntity<ApiResponse<Boolean>> deleteCourse(Long id);
}
