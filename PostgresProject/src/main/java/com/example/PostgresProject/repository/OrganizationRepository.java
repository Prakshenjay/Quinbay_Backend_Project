package com.example.PostgresProject.repository;

import com.example.PostgresProject.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT sc.student.name AS studentName, sc.course.name AS courseName, sc.status AS progress " +
            "FROM StudentCourse sc " +
            "WHERE sc.status = :progress AND sc.student.organization.id = :orgId")
    List<Map<String, Object>> getStudentByCourseProgress(@Param("progress") String progress, @Param("orgId") Long orgId);

}
