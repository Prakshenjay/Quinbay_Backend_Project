package com.example.OpenFeign.feign;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PostgresFeignFallback implements PostgresFeign {

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> addStudent(StudentDTO dto){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Cant Add", "error", null));
    }

    @Override
    public ResponseEntity<ApiResponse<StudentDTO>> getStudent(String id){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Not Found", "error", null));
    }
}
