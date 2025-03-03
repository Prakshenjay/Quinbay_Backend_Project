package com.example.PostgresProject.controllers;

import com.example.PostgresProject.dto.StudentDTO;
import com.example.PostgresProject.services.StudentService;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<ApiResponse<StudentDTO>> addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("/getStudent")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudent(@RequestParam Long id) {
        return studentService.getStudent(id);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<ApiResponse<Boolean>> deleteStudent(@RequestParam Long id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/registerCourse")
    public ResponseEntity<ApiResponse<Boolean>> registerCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.registerCourse(studentId, courseId);
    }

    @DeleteMapping("/withdrawCourse")
    public ResponseEntity<ApiResponse<Boolean>> withdrawCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.withdrawCourse(studentId, courseId);
    }

    @GetMapping("/getCourseProgressView")
    public ResponseEntity<ApiResponse<Map>> getCourseProgressView(@RequestParam Long id) {
        return studentService.getCourseProgressView(id);
    }
}
