package com.ManagementApplication.StudentManagementSystem.service;

import com.ManagementApplication.StudentManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Integer id);

    StudentDTO getStudentByStudentId(String studentId);

    StudentDTO getStudentByUserId(Integer userId);

    List<StudentDTO> getAllStudents();

    List<StudentDTO> getStudentsByDepartment(String department);

    List<StudentDTO> getStudentsByEnrollmentYear(Integer enrollmentYear);

    List<StudentDTO> getStudentsByCurrentSemester(String currentSemester);

    StudentDTO updateStudent(Integer id, StudentDTO studentDTO);

    void deleteStudent(Integer id);

    boolean isStudentIdExists(String studentId);
}

