package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.StudentDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StudentService {
    ApiResponse<StudentDTO> addStudent(StudentDTO studentDTO);
    ApiResponse<String> getStudent(String id);
    ApiResponse<Boolean> deleteStudent(String id);
    ApiResponse<Boolean> registerCourse(String studentId, String courseId);
    ApiResponse<Boolean> withdrawCourse(String studentId, String courseId);
    ApiResponse<Map> getCourseProgressView(String studentId);
}
