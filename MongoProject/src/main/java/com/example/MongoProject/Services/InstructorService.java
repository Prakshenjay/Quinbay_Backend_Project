package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.InstructorDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface InstructorService {
    ResponseEntity<ApiResponse<InstructorDTO>> addInstructor(InstructorDTO instructorDTO);
    ResponseEntity<ApiResponse<InstructorDTO>> getInstructor(String id);
    ResponseEntity<ApiResponse<Boolean>> deleteInstructor(String id);
    ResponseEntity<ApiResponse<Boolean>> enrollCourse(String instructorId, String courseId);
    ResponseEntity<ApiResponse<Boolean>> withdrawCourse(String instructorId, String courseId);
    ResponseEntity<ApiResponse<Boolean>> updateCourseStatus(String studentId, String courseId, String status);
}
