package com.example.PostgresProject.services;

import com.example.PostgresProject.dto.OrganizationDTO;
import com.example.PostgresProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OrganizationService {
    ApiResponse<OrganizationDTO> addOrganization(OrganizationDTO organizationDTO);
    ApiResponse<OrganizationDTO> getOrganization(Long id);
    ApiResponse<Boolean> deleteOrganization(Long id);
    ApiResponse<Integer> getStudentCount(Long id);
    ApiResponse<Map> getStudentCountForEachCourse(Long id);
    ApiResponse<Map> getInstructorDetailsForEachCourse(Long id);
    ApiResponse<Integer> getInstructorCount(Long id);
    ApiResponse<Map> getStudentCourseInstructorByCourse(Long id);
    ApiResponse<Object> getStudentByCourseProgress(Long id, String progress);
}
