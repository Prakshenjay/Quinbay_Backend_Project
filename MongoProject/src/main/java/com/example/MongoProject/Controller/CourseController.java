package com.example.MongoProject.Controller;

import com.example.MongoProject.DTO.CourseDTO;
import com.example.MongoProject.Services.CourseService;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public ApiResponse<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @GetMapping("/getCourse")
    public ApiResponse<CourseDTO> getCourse(@RequestParam String id) {
        return courseService.getCourse(id);
    }

    @DeleteMapping("/deleteCourse")
    public ApiResponse<Boolean> deleteCourse(@RequestParam String id) {
        return courseService.deleteCourse(id);
    }
}
