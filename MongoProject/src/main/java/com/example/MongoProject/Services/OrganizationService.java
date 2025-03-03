package com.example.MongoProject.Services;

import com.example.MongoProject.DTO.OrganizationDTO;
import com.example.MongoProject.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OrganizationService {
    ApiResponse<OrganizationDTO> addOrganization(OrganizationDTO organizationDTO);
    ApiResponse<OrganizationDTO> getOrganization(String id);
    ApiResponse<Boolean> deleteOrganization(String id);
    ApiResponse<Integer> getStudentCount(String id);
    ApiResponse<Map> getStudentCountForEachCourse(String id);
    ApiResponse<Map> getInstructorDetailsForEachCourse(String id);
    ApiResponse<Integer> getInstructorCount(String id);
    ApiResponse<Map> getStudentCourseInstructorByCourse(String id);
    ApiResponse<Object> getStudentByCourseProgress(String id, String progress);
}
