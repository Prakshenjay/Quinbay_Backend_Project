package com.example.OpenFeign.Service.impl;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.Service.StudentService;
import com.example.OpenFeign.feign.MongoFeign;
import com.example.OpenFeign.feign.PostgresFeign;
import com.example.OpenFeign.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    PostgresFeign postgresFeign;

    @Autowired
    MongoFeign mongoFeign;

    @Override
    public ApiResponse<StudentDTO> addStudent(StudentDTO dto, int feignChoice){
        log.info("Adding student: {} with feignChoice: {}", dto, feignChoice);

        if(feignChoice == 1) {
            log.info("Using PostgresFeign to add student");
            return postgresFeign.addStudent(dto);
        }
        else if(feignChoice == 2){
            log.info("Using MongoFeign to add student");
            return mongoFeign.addStudent(dto);
        }
        else{
            log.warn("Invalid feignChoice: {}", feignChoice);
            return (new ApiResponse<>("Invalid Operation", "Choose Option 1 or 2", null));
        }
    }

    @Override
    public ApiResponse<StudentDTO> getStudent(String id, int feignChoice){
        log.info("Fetching student with id: {} using feignChoice: {}", id, feignChoice);

        if(feignChoice == 1) {
            log.info("Using PostgresFeign to get student with id: {}", id);
            Long postgresId = Long.parseLong(id);
            return postgresFeign.getStudent(id);
        }
        else if(feignChoice == 2){
            log.info("Using MongoFeign to get student with id: {}", id);
            return mongoFeign.getStudent(id);
        }
        else{
            log.warn("Invalid feignChoice: {}", feignChoice);
            return (new ApiResponse<>("Invalid Operation", "Choose Option 1 or 2", null));
        }
    }
}
