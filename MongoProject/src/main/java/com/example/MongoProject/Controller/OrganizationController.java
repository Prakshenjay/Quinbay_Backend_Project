package com.example.MongoProject.Controller;

import com.example.MongoProject.DTO.OrganizationDTO;
import com.example.MongoProject.Services.OrganizationService;
import com.example.MongoProject.utils.ApiResponse;
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
    public ApiResponse<OrganizationDTO> addOrganization(@RequestBody OrganizationDTO organizationDTO) {
        return organizationService.addOrganization(organizationDTO);
    }

    @GetMapping("/getOrganization")
    public ApiResponse<OrganizationDTO> getOrganization(@RequestParam String id) {
        return organizationService.getOrganization(id);
    }

    @DeleteMapping("/deleteOrganization")
    public ApiResponse<Boolean> deleteOrganization(@RequestParam String id) {
        return organizationService.deleteOrganization(id);
    }

    @GetMapping("/getStudentCount")
    public ApiResponse<Integer> getStudentCount(@RequestParam String id) {
        return organizationService.getStudentCount(id);
    }

    @GetMapping("/getStudentCountForEachCourse")
    public ApiResponse<Map> getStudentCountForEachCourse(@RequestParam String id) {
        return organizationService.getStudentCountForEachCourse(id);
    }

    @GetMapping("/getInstructorDetailsForEachCourse")
    public ApiResponse<Map> getInstructorDetailsForEachCourse(@RequestParam String id) {
        return organizationService.getInstructorDetailsForEachCourse(id);
    }

    @GetMapping("/getInstructorCount")
    public ApiResponse<Integer> getInstructorCount(@RequestParam String id) {
        return organizationService.getInstructorCount(id);
    }

    @GetMapping("/getStudentCourseInstructorByCourse")
    public ApiResponse<Map> getStudentCourseInstructorByCourse(@RequestParam String id) {
        return organizationService.getStudentCourseInstructorByCourse(id);
    }

    @GetMapping("/getStudentByCourseProgress")
    public ApiResponse<Object> getStudentByCourseProgress(@RequestParam String id, @RequestParam String progress) {
        return organizationService.getStudentByCourseProgress(id, progress);
    }
}
