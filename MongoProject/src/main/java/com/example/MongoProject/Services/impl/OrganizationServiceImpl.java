package com.example.MongoProject.Services.impl;

import com.example.MongoProject.DTO.OrganizationDTO;
import com.example.MongoProject.Entity.Course;
import com.example.MongoProject.Entity.Organization;
import com.example.MongoProject.Repository.CourseRepository;
import com.example.MongoProject.Repository.OrganizationRepository;
import com.example.MongoProject.Services.OrganizationService;
import com.example.MongoProject.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @CachePut(value = "organizations", key = "#organizationDTO.id")
    public ApiResponse<OrganizationDTO> addOrganization(OrganizationDTO organizationDTO){
        log.info("Attempting to add organization: {}", organizationDTO);
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDTO, organization);
        organization = organizationRepository.save(organization);
        BeanUtils.copyProperties(organization, organizationDTO);
        log.info("Organization added successfully: {}", organizationDTO);
        return (new ApiResponse<>("success", "Organization Created Successfully", organizationDTO));
    }

    @Override
    @Cacheable(value = "organizations", key = "#id")
    public ApiResponse<OrganizationDTO> getOrganization(String id){
        log.info("Fetching organization with ID: {}", id);
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null) {
            log.warn("Organization not found with ID: {}", id);
            return (new ApiResponse<>("Organization Not Found", "error"));
        }
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        log.info("Organization found: {}", organizationDTO);
        return (new ApiResponse<>("success", "Organization Found", organizationDTO));
    }

    @Override
    @CacheEvict(value = "organizations", key = "#id")
    public ApiResponse<Boolean> deleteOrganization(String id){
        log.info("Attempting to delete organization with ID: {}", id);
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization != null) {
            organizationRepository.deleteById(id);
            log.info("Organization deleted successfully with ID: {}", id);
            return (new ApiResponse<>("success", "Organization Successfully Deleted", true));
        }
        log.warn("Organization not found for deletion with ID: {}", id);
        return (new ApiResponse<>("Organization Not Deleted, Id Not Found", "error", false));
    }

    @Override
    public ApiResponse<Integer> getStudentCount(String id){
        log.info("Fetching student count for organization ID: {}", id);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Organization ID"));
        int count = organization.getStudents().size();
        log.info("Student count retrieved: {}", count);
        return (new ApiResponse<>("success", "Student Count retrieved", count));
    }

    @Override
    public ApiResponse<Map> getStudentCountForEachCourse(String id){
        log.info("Fetching student count for each course in organization ID: {}", id);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Organization ID"));
        List<Course> courses = organization.getCourses();
        Map<String, Integer> count = new HashMap<>();
        courses.forEach(course -> count.put(course.getName(), course.getStudentCourses().size()));
        log.info("Student count for each course: {}", count);
        return (new ApiResponse<>("success", "Student count for each course fetched", count));
    }

    @Override
    public ApiResponse<Map> getInstructorDetailsForEachCourse(String id){
        log.info("Fetching instructor details for each course in organization ID: {}", id);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Organization ID"));
        List<Course> courses = organization.getCourses();
        Map<String, String> details = new HashMap<>();
        courses.forEach(course -> details.put(course.getName(), course.getInstructor().toString()));
        log.info("Instructor details for each course: {}", details);
        return (new ApiResponse<>("success", "Instructor Details for each course fetched", details));
    }

    @Override
    public ApiResponse<Integer> getInstructorCount(String id){
        log.info("Fetching instructor count for organization ID: {}", id);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Organization ID"));
        int count = organization.getInstructors().size();
        log.info("Instructor count retrieved: {}", count);
        return (new ApiResponse<>("success", "Instructor Count retrieved", count));
    }

    @Override
    public ApiResponse<Map> getStudentCourseInstructorByCourse(String id){
        log.info("Fetching course, student, and instructor details for course ID: {}", id);
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course ID Invalid"));
        Map<String, Object> details = new HashMap<>();
        details.put("Course", course.toString());
        details.put("Instructor", course.getInstructor().toString());
        Map<String, String> studentDetails = new HashMap<>();
        course.getStudentCourses().forEach(sc -> studentDetails.put(sc.getStudent().toString(), sc.getStatus()));
        details.put("Students", studentDetails);
        log.info("Course, student, and instructor details: {}", details);
        return (new ApiResponse<>("success", "Course,Student,Instructor Details for course fetched", details));
    }

    @Override
    public ApiResponse<Object> getStudentByCourseProgress(String id, String progress){
        log.info("Fetching students with progress '{}' for organization ID: {}", progress, id);
        Object details = organizationRepository.getStudentByCourseProgress(progress, id);
        log.info("Students by progress: {}", details);
        return (new ApiResponse<>("success", "Student by Progress fetched", details));
    }
}
