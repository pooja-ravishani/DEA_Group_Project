package com.ManagementApplication.StudentManagementSystem.service;

import com.studentmanagement.StudentManagementSystem.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(Integer id);

    CourseDTO getCourseByCourseCode(String courseCode);

    List<CourseDTO> getAllCourses();

    List<CourseDTO> getCoursesByDepartment(String department);

    List<CourseDTO> getCoursesByTeacherId(Integer teacherId);

    List<CourseDTO> getActiveCourses();

    CourseDTO updateCourse(Integer id, CourseDTO courseDTO);

    void deleteCourse(Integer id);

    boolean isCourseCodeExists(String courseCode);

    void assignTeacherToCourse(Integer courseId, Integer teacherId);

    void activateCourse(Integer id);

    void deactivateCourse(Integer id);
}
