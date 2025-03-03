package com.example.OpenFeign.feign;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Mongo-Client", url = "http://localhost:9099/api/student")
public interface MongoFeign {

    @PostMapping("/addStudent")
    ApiResponse<StudentDTO> addStudent(@RequestBody StudentDTO dto);

    @GetMapping("/getStudent")
    ApiResponse<StudentDTO> getStudent(@RequestParam String id);
}
