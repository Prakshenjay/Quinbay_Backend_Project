package com.example.PostgresProject.services.impl;

import com.example.PostgresProject.dto.StudentDTO;
import com.example.PostgresProject.entity.Course;
import com.example.PostgresProject.entity.Student;
import com.example.PostgresProject.entity.StudentCourse;
import com.example.PostgresProject.repository.CourseRepository;
import com.example.PostgresProject.repository.OrganizationRepository;
import com.example.PostgresProject.repository.StudentCourseRepository;
import com.example.PostgresProject.repository.StudentRepository;
import com.example.PostgresProject.services.StudentService;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Override
    @CachePut(value = "students", key = "#studentDTO.id")
    public ApiResponse<StudentDTO> addStudent(StudentDTO studentDTO){
        log.info("Adding new student: {}", studentDTO);
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);

        student.setOrganization(organizationRepository.findById(studentDTO.getOrganizationId()).orElseThrow(() -> new RuntimeException("Incorrect Organization ID Provided")));

        List<Course> courses = courseRepository.findAllById(studentDTO.getCourseIds());
        if(!courses.isEmpty()){
            List<StudentCourse> studentCourses = courses.stream().map(course -> {
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setCourse(course);
                studentCourse.setStudent(student);
                studentCourse.setStatus("TO_DO");
                course.getStudentCourses().add(studentCourse);
                courseRepository.save(course);
                return studentCourse;
            }).collect(Collectors.toList());

            studentCourseRepository.saveAll(studentCourses);
            student.setStudentCourse(studentCourses);
        }

        studentRepository.save(student);

        BeanUtils.copyProperties(student, studentDTO);
        log.info("Student added successfully: {}", studentDTO);
        return (new ApiResponse<>("success", "Student Saved Successfully", studentDTO));
    }

    @Override
    @Cacheable(value = "students", key = "#id")
    public ApiResponse<StudentDTO> getStudent(Long id){
        log.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            log.warn("Student not found with ID: {}", id);
            return (new ApiResponse<>("Not Found", "error", null));
        }

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setOrganizationId(student.getOrganization().getId());
        studentDTO.setName(student.getName());
        studentDTO.setDateOfBirth(student.getDateOfBirth());
        List<Long> courseIds = student.getStudentCourse().stream().map(sc -> sc.getCourse().getId()).collect(Collectors.toList());
        studentDTO.setCourseIds(courseIds);

        log.info("Student fetched successfully: {}", studentDTO);
        return (new ApiResponse<>("success", "Student is Present", studentDTO));
    }

    @Override
    @CacheEvict(value = "students", key = "#id")
    public ApiResponse<Boolean> deleteStudent(Long id){
        log.info("Deleting student with ID: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.deleteById(id);
            log.info("Student deleted successfully with ID: {}", id);
            return (new ApiResponse<>("success", "Student Successfully Deleted", true));
        }
        log.warn("Failed to delete student with ID: {}", id);
        return (new ApiResponse<>("Student Not Deleted", "error", false));
    }

    @Override
    public ApiResponse<Boolean> registerCourse(Long studentId, Long courseId){
        log.info("Registering student {} for course {}", studentId, courseId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Id Invalid"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Id Invalid"));

        StudentCourse sc = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        if(sc != null){
            log.warn("Student {} already enrolled in course {}", studentId, courseId);
            return (new ApiResponse<>("Student Already enrolled", "error", false));
        }

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(course);
        studentCourse.setStudent(student);
        studentCourse.setStatus("TO_DO");
        studentCourseRepository.save(studentCourse);

        student.getStudentCourse().add(studentCourse);
        studentRepository.save(student);

        log.info("Student {} successfully enrolled in course {}", studentId, courseId);
        return (new ApiResponse<>("success", "Student Successfully Enrolled to Course", true));
    }

    @Override
    public ApiResponse<Boolean> withdrawCourse(Long studentId, Long courseId){
        log.info("Withdrawing student {} from course {}", studentId, courseId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Id Invalid"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Id Invalid"));

        StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        if(studentCourse == null){
            log.warn("Student {} not enrolled in course {}", studentId, courseId);
            return (new ApiResponse<>("Student Not enrolled", "error", false));
        }

        course.getStudentCourses().remove(studentCourse);
        student.getStudentCourse().remove(studentCourse);
        studentCourseRepository.delete(studentCourse);
        studentRepository.save(student);
        courseRepository.save(course);

        log.info("Student {} successfully withdrawn from course {}", studentId, courseId);
        return (new ApiResponse<>("success", "Student Successfully Withdrawn from Course", true));
    }

    @Override
    public ApiResponse<Map> getCourseProgressView(Long studentId){
        log.info("Fetching course progress for student {}", studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Id Invalid"));

        Map<String, String> progress = new HashMap<>();
        student.getStudentCourse().forEach(studentCourse -> {
            progress.put(studentCourse.getCourse().getName(), studentCourse.getStatus());
        });

        log.info("Course progress retrieved for student {}: {}", studentId, progress);
        return (new ApiResponse<>("success", "Student Course Progress Retrieved", progress));
    }
}
