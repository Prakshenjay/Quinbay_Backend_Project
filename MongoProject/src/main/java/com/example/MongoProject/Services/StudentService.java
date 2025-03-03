package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.StudentDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StudentService {
    ResponseEntity<ApiResponse<StudentDTO>> addStudent(StudentDTO studentDTO);
    ResponseEntity<ApiResponse<String>> getStudent(String id);
    ResponseEntity<ApiResponse<Boolean>> deleteStudent(String id);
    ResponseEntity<ApiResponse<Boolean>> registerCourse(String studentId, String courseId);
    ResponseEntity<ApiResponse<Boolean>> withdrawCourse(String studentId, String courseId);
    ResponseEntity<ApiResponse<Map>> getCourseProgressView(String studentId);
}
