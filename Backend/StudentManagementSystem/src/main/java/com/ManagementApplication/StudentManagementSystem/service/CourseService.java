package com.ManagementApplication.StudentManagementSystem.service;

import com.ManagementApplication.StudentManagementSystem.dto.CourseDto;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();

    CourseDto getCourseById(Long id) throws ResourceNotFoundException;

    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(Long id, CourseDto courseDto) throws ResourceNotFoundException;

    void deleteCourse(Long id) throws ResourceNotFoundException;
}
