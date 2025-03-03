package com.example.MongoProject.Controller;

import com.example.MongoProject.DTO.StudentDTO;
import com.example.MongoProject.Services.StudentService;
import com.example.MongoProject.utils.ApiResponse;
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
    public ResponseEntity<ApiResponse<String>> getStudent(@RequestParam String id) {
        return studentService.getStudent(id);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<ApiResponse<Boolean>> deleteStudent(@RequestParam String id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/registerCourse")
    public ResponseEntity<ApiResponse<Boolean>> registerCourse(@RequestParam String studentId, @RequestParam String courseId) {
        return studentService.registerCourse(studentId, courseId);
    }

    @DeleteMapping("/withdrawCourse")
    public ResponseEntity<ApiResponse<Boolean>> withdrawCourse(@RequestParam String studentId, @RequestParam String courseId) {
        return studentService.withdrawCourse(studentId, courseId);
    }

    @GetMapping("/getCourseProgressView")
    public ResponseEntity<ApiResponse<Map>> getCourseProgressView(@RequestParam String id) {
        return studentService.getCourseProgressView(id);
    }
}
