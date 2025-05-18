package com.ManagementApplication.StudentManagementSystem.repository;

import com.ManagementApplication.StudentManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseName(String courseName);

    List<Course> findByCourseCode(String courseCode);
}
