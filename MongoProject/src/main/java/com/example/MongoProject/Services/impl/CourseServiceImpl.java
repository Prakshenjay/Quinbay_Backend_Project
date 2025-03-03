package com.example.MongoProject.Services.impl;

import com.example.MongoProject.DTO.CourseDTO;
import com.example.MongoProject.Entity.Course;
import com.example.MongoProject.Entity.Instructor;
import com.example.MongoProject.Repository.CourseRepository;
import com.example.MongoProject.Repository.InstructorRepository;
import com.example.MongoProject.Repository.OrganizationRepository;
import com.example.MongoProject.Services.CourseService;
import com.example.MongoProject.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @CachePut(value = "courses", key = "#courseDTO.id")
    public ApiResponse<CourseDTO> addCourse(CourseDTO courseDTO){
        log.info("Attempting to add a new course: {}", courseDTO);
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                .orElseThrow(() -> {
                    log.error("Invalid Instructor ID Provided: {}", courseDTO.getInstructorId());
                    return new RuntimeException("Invalid Instructor ID Provided");
                });

        if(instructor.getCourse() != null){
            log.warn("Instructor already assigned to another course: {}", instructor.getCourse().getId());
            return (new ApiResponse<>("failed", "Instructor already has a course", null));
        }

        course.setInstructor(instructor);
        course.setOrganization(organizationRepository.findById(courseDTO.getOrganizationId())
                .orElseThrow(() -> {
                    log.error("Invalid Organization ID Provided: {}", courseDTO.getOrganizationId());
                    return new RuntimeException("Invalid Organization ID Provided");
                }));

        course = courseRepository.save(course);
        log.info("Course added successfully with ID: {}", course.getId());

        BeanUtils.copyProperties(course, courseDTO);
        return (new ApiResponse<>("success", "Course saved Successfully", courseDTO));
    }

    @Override
    @Cacheable(value = "courses", key = "#id")
    public ApiResponse<CourseDTO> getCourse(String id){
        log.info("Fetching course with ID: {}", id);
        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            log.warn("Course not found with ID: {}", id);
            return (new ApiResponse<>("Not Found", "error"));
        }

        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);

        log.info("Course found: {}", courseDTO);
        return (new ApiResponse<>("success", "Course is Present", courseDTO));
    }

    @Override
    @CacheEvict(value = "courses", key = "#id")
    public ApiResponse<Boolean> deleteCourse(String id){
        log.info("Attempting to delete course with ID: {}", id);
        Course course = courseRepository.findById(id).orElse(null);

        if (course != null) {
            courseRepository.deleteById(id);
            log.info("Course successfully deleted with ID: {}", id);
            return (new ApiResponse<>("success", "Course Successfully Deleted", true));
        }

        log.warn("Course not found for deletion with ID: {}", id);
        return (new ApiResponse<>("Course Not Deleted", "error", false));
    }
}
