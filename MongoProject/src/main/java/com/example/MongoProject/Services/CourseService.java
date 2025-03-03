package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.CourseDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    ResponseEntity<ApiResponse<CourseDTO>> addCourse(CourseDTO courseDTO);
    ResponseEntity<ApiResponse<CourseDTO>> getCourse(String id);
    ResponseEntity<ApiResponse<Boolean>> deleteCourse(String id);
}
