package com.example.MongoProject.Repository;

import com.example.MongoProject.Entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
    @Query("SELECT sc.student.name AS studentName, sc.course.name AS courseName, sc.status AS progress " +
            "FROM StudentCourse sc " +
            "WHERE sc.status = :progress AND sc.student.organization.id = :orgId")
    List<Map<String, Object>> getStudentByCourseProgress(@Param("progress") String progress, @Param("orgId") String orgId);

}
