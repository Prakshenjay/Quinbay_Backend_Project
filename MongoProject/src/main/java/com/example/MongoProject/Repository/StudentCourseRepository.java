package com.example.MongoProject.Repository;

import com.example.MongoProject.Entity.StudentCourse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends MongoRepository<StudentCourse, String> {
    StudentCourse findByStudentIdAndCourseId(String StudentId, String CourseID);
}
