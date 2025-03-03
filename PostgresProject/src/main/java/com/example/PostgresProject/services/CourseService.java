package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.CourseDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    ApiResponse<CourseDTO> addCourse(CourseDTO courseDTO);
    ApiResponse<CourseDTO> getCourse(Long id);
    ApiResponse<Boolean> deleteCourse(Long id);
}
