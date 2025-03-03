package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.CourseDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    ApiResponse<CourseDTO> addCourse(CourseDTO courseDTO);
    ApiResponse<CourseDTO> getCourse(String id);
    ApiResponse<Boolean> deleteCourse(String id);
}
