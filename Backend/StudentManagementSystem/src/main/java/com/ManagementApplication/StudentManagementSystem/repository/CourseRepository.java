package com.ManagementApplication.StudentManagementSystem.repository;

import com.studentmanagement.StudentManagementSystem.entity.Course;
import com.studentmanagement.StudentManagementSystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByCourseCode(String courseCode);

    List<Course> findByCourseName(String courseName);

    List<Course> findByDepartment(String department);

    List<Course> findByTeacher(Teacher teacher);

    List<Course> findByActive(Boolean active);

    boolean existsByCourseCode(String courseCode);

    List<Course> findByTeacherId(Integer teacherId);

    List<Course> findByActiveTrue();
}
