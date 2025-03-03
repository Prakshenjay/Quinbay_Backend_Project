package com.example.PostgresProject.services.impl;

import com.example.PostgresProject.dto.InstructorDTO;
import com.example.PostgresProject.entity.*;
import com.example.PostgresProject.repository.*;
import com.example.PostgresProject.services.InstructorService;
import com.example.PostgresProject.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Override
    @CachePut(value = "instructors", key = "#instructorDTO.id")
    public ApiResponse<InstructorDTO> addInstructor(InstructorDTO instructorDTO){
        log.info("Adding new instructor: {}", instructorDTO);
        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(instructorDTO, instructor);

        Organization organization = organizationRepository.findById(instructorDTO.getOrganizationId())
                .orElseThrow(() -> {
                    log.error("Organization not found with ID: {}", instructorDTO.getOrganizationId());
                    return new RuntimeException("Organization Not Found");
                });
        instructor.setOrganization(organization);

        instructor = instructorRepository.save(instructor);
        BeanUtils.copyProperties(instructor, instructorDTO);

        log.info("Instructor added successfully: {}", instructorDTO);
        return (new ApiResponse<>("success", "Instructor Saved Successfully", instructorDTO));
    }

    @Override
    @Cacheable(value = "instructors", key = "#id")
    public ApiResponse<InstructorDTO> getInstructor(Long id){
        log.info("Fetching instructor with ID: {}", id);
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor == null) {
            log.warn("Instructor not found with ID: {}", id);
            return (new ApiResponse<>("Instructor Not Found", "error"));
        }

        InstructorDTO instructorDTO = new InstructorDTO();
        BeanUtils.copyProperties(instructor, instructorDTO);
        instructorDTO.setOrganizationId(instructor.getOrganization().getId());

        log.info("Instructor found: {}", instructorDTO);
        return (new ApiResponse<>("success", "Instructor Found", instructorDTO));
    }

    @Override
    @CacheEvict(value = "instructors", key = "#id")
    public ApiResponse<Boolean> deleteInstructor(Long id){
        log.info("Deleting instructor with ID: {}", id);
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor != null) {
            instructorRepository.deleteById(id);
            log.info("Instructor successfully deleted with ID: {}", id);
            return (new ApiResponse<>("success", "Instructor Successfully Deleted", true));
        }
        log.warn("Instructor not found for deletion with ID: {}", id);
        return (new ApiResponse<>("Instructor Not Deleted", "error", false));
    }

    @Override
    public ApiResponse<Boolean> enrollCourse(Long instructorId, Long courseId){
        log.info("Enrolling instructor {} to course {}", instructorId, courseId);
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> {
                    log.error("Instructor not found with ID: {}", instructorId);
                    return new RuntimeException("Instructor not found");
                });
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course not found with ID: {}", courseId);
                    return new RuntimeException("Course not found");
                });

        if(instructor.getCourse() != null && instructor.getCourse().getId().equals(courseId)){
            log.warn("Instructor already enrolled to course {}", courseId);
            return (new ApiResponse<>("Instructor Already enrolled", "error", false));
        }

        instructor.setCourse(course);
        instructorRepository.save(instructor);
        course.setInstructor(instructor);
        courseRepository.save(course);
        log.info("Instructor {} successfully enrolled to course {}", instructorId, courseId);
        return (new ApiResponse<>("success", "Instructor Successfully Enrolled to Course", true));
    }

    @Override
    public ApiResponse<Boolean> withdrawCourse(Long instructorId, Long courseId){
        log.info("Withdrawing instructor {} from course {}", instructorId, courseId);
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (instructor.getCourse() == course) {
            instructor.setCourse(null);
            instructorRepository.save(instructor);
            course.setInstructor(null);
            courseRepository.save(course);
            log.info("Instructor {} successfully withdrawn from course {}", instructorId, courseId);
            return (new ApiResponse<>("success", "Instructor Successfully Withdrawn from Course", true));
        }
        log.warn("Instructor {} not enrolled in course {}", instructorId, courseId);
        return (new ApiResponse<>("Instructor Not enrolled", "error", false));
    }

    @Override
    public ApiResponse<Boolean> updateCourseStatus(Long studentId, Long courseId, String status){
        log.info("Updating course status for student {} and course {} to status {}", studentId, courseId, status);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if(!status.equalsIgnoreCase("TO_DO") && !status.equalsIgnoreCase("IN_PROGRESS") && !status.equalsIgnoreCase("COMPLETED")){
            log.error("Invalid course status: {}", status);
            return (new ApiResponse<>("Invalid", "Incorrect Status", false));
        }

        StudentCourse sc = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        if(sc == null){
            sc = new StudentCourse();
            sc.setStudent(student);
            sc.setCourse(course);
            sc.setStatus(status.toUpperCase());

            student.getStudentCourse().add(sc);
            course.getStudentCourses().add(sc);

            studentRepository.save(student);
            courseRepository.save(course);
            studentCourseRepository.save(sc);

            log.info("Course status set to {} for student {} and course {}", status, studentId, courseId);
            return (new ApiResponse<>("success", "Course Status Set", true));
        }

        sc.setStatus(status.toUpperCase());
        studentCourseRepository.save(sc);

        log.info("Course status updated to {} for student {} and course {}", status, studentId, courseId);
        return (new ApiResponse<>("success", "Course Status Set", true));
    }
}
