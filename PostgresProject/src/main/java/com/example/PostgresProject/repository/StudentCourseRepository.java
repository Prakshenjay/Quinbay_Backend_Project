package com.example.PostgresProject.repository;

import com.example.PostgresProject.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    StudentCourse findByStudentIdAndCourseId(Long StudentId, Long CourseID);
}
