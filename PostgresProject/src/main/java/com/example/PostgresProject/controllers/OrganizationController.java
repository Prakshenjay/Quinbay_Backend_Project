package com.example.PostgresProject.controllers;

import com.example.PostgresProject.dto.OrganizationDTO;
import com.example.PostgresProject.services.OrganizationService;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/addOrganization")
    public ResponseEntity<ApiResponse<OrganizationDTO>> addOrganization(@RequestBody OrganizationDTO organizationDTO) {
        return organizationService.addOrganization(organizationDTO);
    }

    @GetMapping("/getOrganization")
    public ResponseEntity<ApiResponse<OrganizationDTO>> getOrganization(@RequestParam Long id) {
        return organizationService.getOrganization(id);
    }

    @DeleteMapping("/deleteOrganization")
    public ResponseEntity<ApiResponse<Boolean>> deleteOrganization(@RequestParam Long id) {
        return organizationService.deleteOrganization(id);
    }

    @GetMapping("/getStudentCount")
    public ResponseEntity<ApiResponse<Integer>> getStudentCount(@RequestParam Long id) {
        return organizationService.getStudentCount(id);
    }

    @GetMapping("/getStudentCountForEachCourse")
    public ResponseEntity<ApiResponse<Map>> getStudentCountForEachCourse(@RequestParam Long id) {
        return organizationService.getStudentCountForEachCourse(id);
    }

    @GetMapping("/getInstructorDetailsForEachCourse")
    public ResponseEntity<ApiResponse<Map>> getInstructorDetailsForEachCourse(@RequestParam Long id) {
        return organizationService.getInstructorDetailsForEachCourse(id);
    }

    @GetMapping("/getInstructorCount")
    public ResponseEntity<ApiResponse<Integer>> getInstructorCount(@RequestParam Long id) {
        return organizationService.getInstructorCount(id);
    }

    @GetMapping("/getStudentCourseInstructorByCourse")
    public ResponseEntity<ApiResponse<Map>> getStudentCourseInstructorByCourse(@RequestParam Long id) {
        return organizationService.getStudentCourseInstructorByCourse(id);
    }

    @GetMapping("/getStudentByCourseProgress")
    public ResponseEntity<ApiResponse<Object>> getStudentByCourseProgress(@RequestParam Long id, @RequestParam String progress) {
        return organizationService.getStudentByCourseProgress(id, progress);
    }
}
