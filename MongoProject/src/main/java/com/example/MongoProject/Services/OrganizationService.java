package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.OrganizationDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OrganizationService {
    ResponseEntity<ApiResponse<OrganizationDTO>> addOrganization(OrganizationDTO organizationDTO);
    ResponseEntity<ApiResponse<OrganizationDTO>> getOrganization(String id);
    ResponseEntity<ApiResponse<Boolean>> deleteOrganization(String id);
    ResponseEntity<ApiResponse<Integer>> getStudentCount(String id);
    ResponseEntity<ApiResponse<Map>> getStudentCountForEachCourse(String id);
    ResponseEntity<ApiResponse<Map>> getInstructorDetailsForEachCourse(String id);
    ResponseEntity<ApiResponse<Integer>> getInstructorCount(String id);
    ResponseEntity<ApiResponse<Map>> getStudentCourseInstructorByCourse(String id);
    ResponseEntity<ApiResponse<Object>> getStudentByCourseProgress(String id, String progress);
}
