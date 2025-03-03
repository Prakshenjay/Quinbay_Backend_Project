package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.StudentDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StudentService {
    ApiResponse<StudentDTO> addStudent(StudentDTO studentDTO);
    ApiResponse<StudentDTO> getStudent(Long id);
    ApiResponse<Boolean> deleteStudent(Long id);
    ApiResponse<Boolean> registerCourse(Long studentId, Long courseId);
    ApiResponse<Boolean> withdrawCourse(Long studentId, Long courseId);
    ApiResponse<Map> getCourseProgressView(Long studentId);
}
