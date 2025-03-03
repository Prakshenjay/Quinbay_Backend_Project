package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.StudentDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StudentService {
    ResponseEntity<ApiResponse<StudentDTO>> addStudent(StudentDTO studentDTO);
    ResponseEntity<ApiResponse<StudentDTO>> getStudent(Long id);
    ResponseEntity<ApiResponse<Boolean>> deleteStudent(Long id);
    ResponseEntity<ApiResponse<Boolean>> registerCourse(Long studentId, Long courseId);
    ResponseEntity<ApiResponse<Boolean>> withdrawCourse(Long studentId, Long courseId);
    ResponseEntity<ApiResponse<Map>> getCourseProgressView(Long studentId);
}
