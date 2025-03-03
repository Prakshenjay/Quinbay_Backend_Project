package com.example.OpenFeign.Service;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public ApiResponse<StudentDTO> addStudent(StudentDTO dto, int feignChoice);
    public ApiResponse<StudentDTO> getStudent(String id, int feignChoice);
}
