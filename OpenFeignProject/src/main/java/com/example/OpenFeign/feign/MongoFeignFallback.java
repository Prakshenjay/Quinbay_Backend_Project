package com.example.OpenFeign.feign;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MongoFeignFallback implements MongoFeign {

    @Override
    public ApiResponse<StudentDTO> addStudent(StudentDTO dto){
        return (new ApiResponse<>("Cant Add", "error", null));
    }

    @Override
    public ApiResponse<StudentDTO> getStudent(String id){
        return (new ApiResponse<>("Not Found", "error", null));
    }
}
