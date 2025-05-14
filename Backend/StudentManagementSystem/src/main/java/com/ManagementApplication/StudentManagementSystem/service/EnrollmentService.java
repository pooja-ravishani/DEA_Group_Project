package com.ManagementApplication.StudentManagementSystem.service;

import com.studentmanagement.StudentManagementSystem.dto.EnrollmentDTO;

import java.time.LocalDateTime;

import java.util.List;

public interface EnrollmentService {

    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO getEnrollmentById(Integer id);

    List<EnrollmentDTO> getAllEnrollments();

    List<EnrollmentDTO> getEnrollmentsByStudentId(Integer studentId);

    List<EnrollmentDTO> getEnrollmentsByCourseId(Integer courseId);

    List<EnrollmentDTO> getEnrollmentsByStudentIdAndSemester(Integer studentId, String semester, String academicYear);

    List<EnrollmentDTO> getEnrollmentsByCourseIdAndSemester(Integer courseId, String semester, String academicYear);

    List<EnrollmentDTO> getEnrollmentsByStatus(String status);

    EnrollmentDTO updateEnrollment(Integer id, EnrollmentDTO enrollmentDTO);

    void deleteEnrollment(Integer id);

    EnrollmentDTO enrollStudentInCourse(Integer studentId, Integer courseId, String semester, String academicYear);

    EnrollmentDTO withdrawStudentFromCourse(Integer enrollmentId, LocalDateTime withdrawDate);

    boolean isStudentEnrolledInCourse(Integer studentId, Integer courseId, String semester, String academicYear);
}
