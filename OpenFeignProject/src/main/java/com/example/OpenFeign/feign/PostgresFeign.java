package com.example.OpenFeign.feign;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Postgres-Client", url = "http://localhost:8085/api/student")
public interface PostgresFeign {

    @PostMapping("/addStudent")
    ResponseEntity<ApiResponse<StudentDTO>> addStudent(StudentDTO dto);

    @GetMapping("/getStudent")
    ResponseEntity<ApiResponse<StudentDTO>> getStudent(@RequestParam("id") String id);
}
