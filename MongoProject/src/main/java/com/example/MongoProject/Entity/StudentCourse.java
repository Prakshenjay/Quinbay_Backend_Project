package com.example.MongoProject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = StudentCourse.COLLECTION_NAME)
public class StudentCourse implements Serializable {
    public static final String COLLECTION_NAME = "StudentCourse";

    @Id
    private String id;

    @DBRef
    private Course course;

    @DBRef
    private Student student;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "course=" + course.getName() +
                ", student=" + student.getName() +
                ", status='" + status + '\'' +
                '}';
    }
}
