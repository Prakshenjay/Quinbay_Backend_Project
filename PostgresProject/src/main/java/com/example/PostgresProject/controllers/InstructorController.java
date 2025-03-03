package com.example.PostgresProject.controllers;

import com.example.PostgresProject.dto.InstructorDTO;
import com.example.PostgresProject.services.InstructorService;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/addInstructor")
    public ResponseEntity<ApiResponse<InstructorDTO>> addInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.addInstructor(instructorDTO);
    }

    @GetMapping("/getInstructor")
    public ResponseEntity<ApiResponse<InstructorDTO>> getInstructor(@RequestParam Long id) {
        return instructorService.getInstructor(id);
    }

    @DeleteMapping("/deleteInstructor")
    public ResponseEntity<ApiResponse<Boolean>> deleteInstructor(@RequestParam Long id) {
        return instructorService.deleteInstructor(id);
    }

    @PostMapping("/enrollCourse")
    public ResponseEntity<ApiResponse<Boolean>> enrollCourse(@RequestParam Long instructorId, @RequestParam Long courseId) {
        return instructorService.enrollCourse(instructorId, courseId);
    }

    @DeleteMapping("/withdrawCourse")
    public ResponseEntity<ApiResponse<Boolean>> withdrawCourse(@RequestParam Long instructorId, @RequestParam Long courseId) {
        return instructorService.withdrawCourse(instructorId, courseId);
    }

    @PostMapping("/updateCourseStatus")
    public ResponseEntity<ApiResponse<Boolean>> updateCourseStatus(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam String status) {
        return instructorService.updateCourseStatus(studentId, courseId, status);
    }
}
