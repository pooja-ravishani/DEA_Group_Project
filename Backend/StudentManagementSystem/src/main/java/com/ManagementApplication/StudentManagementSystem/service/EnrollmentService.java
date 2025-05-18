package com.ManagementApplication.StudentManagementSystem.service;

import com.ManagementApplication.StudentManagementSystem.dto.EnrollmentDto;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import java.util.List;

public interface EnrollmentService {
    List<EnrollmentDto> getAllEnrollments();

    EnrollmentDto getEnrollmentById(Long id) throws ResourceNotFoundException;

    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto);

    EnrollmentDto updateEnrollmentById(Long id, EnrollmentDto enrollmentDto) throws ResourceNotFoundException;

    void deleteEnrollmentById(Long id) throws ResourceNotFoundException;

    List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId);

    List<EnrollmentDto> getEnrollmentsByCourseId(Long courseId);
}
