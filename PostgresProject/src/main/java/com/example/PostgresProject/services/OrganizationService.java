package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.OrganizationDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OrganizationService {
    ResponseEntity<ApiResponse<OrganizationDTO>> addOrganization(OrganizationDTO organizationDTO);
    ResponseEntity<ApiResponse<OrganizationDTO>> getOrganization(Long id);
    ResponseEntity<ApiResponse<Boolean>> deleteOrganization(Long id);
    ResponseEntity<ApiResponse<Integer>> getStudentCount(Long id);
    ResponseEntity<ApiResponse<Map>> getStudentCountForEachCourse(Long id);
    ResponseEntity<ApiResponse<Map>> getInstructorDetailsForEachCourse(Long id);
    ResponseEntity<ApiResponse<Integer>> getInstructorCount(Long id);
    ResponseEntity<ApiResponse<Map>> getStudentCourseInstructorByCourse(Long id);
    ResponseEntity<ApiResponse<Object>> getStudentByCourseProgress(Long id, String progress);
}
