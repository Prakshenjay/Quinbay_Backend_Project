package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.InstructorDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface InstructorService {
    ApiResponse<InstructorDTO> addInstructor(InstructorDTO instructorDTO);
    ApiResponse<InstructorDTO> getInstructor(String id);
    ApiResponse<Boolean> deleteInstructor(String id);
    ApiResponse<Boolean> enrollCourse(String instructorId, String courseId);
    ApiResponse<Boolean> withdrawCourse(String instructorId, String courseId);
    ApiResponse<Boolean> updateCourseStatus(String studentId, String courseId, String status);
}
