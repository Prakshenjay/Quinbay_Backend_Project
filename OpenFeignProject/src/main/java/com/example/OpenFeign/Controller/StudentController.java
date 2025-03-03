package com.example.OpenFeign.Controller;

import com.example.OpenFeign.DTO.StudentDTO;
import com.example.OpenFeign.Service.StudentService;
import com.example.OpenFeign.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public ApiResponse<StudentDTO> addStudent(@RequestBody StudentDTO dto, @RequestParam int feignChoice){
        return studentService.addStudent(dto, feignChoice);
    }

    @GetMapping("/getStudent")
    public ApiResponse<StudentDTO> getStudent(@RequestParam String id, @RequestParam int feignChoice){
        return studentService.getStudent(id, feignChoice);
    }
}
