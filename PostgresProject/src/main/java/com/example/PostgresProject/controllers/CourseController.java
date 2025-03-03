package com.example.PostgresProject.controllers;

import com.example.PostgresProject.dto.CourseDTO;
import com.example.PostgresProject.services.CourseService;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<ApiResponse<CourseDTO>> addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @GetMapping("/getCourse")
    public ResponseEntity<ApiResponse<CourseDTO>> getCourse(@RequestParam Long id) {
        return courseService.getCourse(id);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<ApiResponse<Boolean>> deleteCourse(@RequestParam Long id) {
        return courseService.deleteCourse(id);
    }
}
