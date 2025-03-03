package com.example.MongoProject.Controller;

import com.example.MongoProject.DTO.InstructorDTO;
import com.example.MongoProject.Services.InstructorService;
import com.example.MongoProject.utils.ApiResponse;
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
    public ResponseEntity<ApiResponse<InstructorDTO>> getInstructor(@RequestParam String id) {
        return instructorService.getInstructor(id);
    }

    @DeleteMapping("/deleteInstructor")
    public ResponseEntity<ApiResponse<Boolean>> deleteInstructor(@RequestParam String id) {
        return instructorService.deleteInstructor(id);
    }

    @PostMapping("/enrollCourse")
    public ResponseEntity<ApiResponse<Boolean>> enrollCourse(@RequestParam String instructorId, @RequestParam String courseId) {
        return instructorService.enrollCourse(instructorId, courseId);
    }

    @DeleteMapping("/withdrawCourse")
    public ResponseEntity<ApiResponse<Boolean>> withdrawCourse(@RequestParam String instructorId, @RequestParam String courseId) {
        return instructorService.withdrawCourse(instructorId, courseId);
    }

    @PostMapping("/updateCourseStatus")
    public ResponseEntity<ApiResponse<Boolean>> updateCourseStatus(@RequestParam String studentId, @RequestParam String courseId, @RequestParam String status) {
        return instructorService.updateCourseStatus(studentId, courseId, status);
    }
}
