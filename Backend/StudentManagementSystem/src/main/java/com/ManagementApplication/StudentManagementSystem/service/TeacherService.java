package com.ManagementApplication.StudentManagementSystem.service;

import com.ManagementApplication.StudentManagementSystem.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(Integer id);

    TeacherDTO getTeacherByEmployeeId(String employeeId);

    TeacherDTO getTeacherByUserId(Integer userId);

    List<TeacherDTO> getAllTeachers();

    List<TeacherDTO> getTeachersByDepartment(String department);

    List<TeacherDTO> getTeachersBySpecialization(String specialization);

    TeacherDTO updateTeacher(Integer id, TeacherDTO teacherDTO);

    void deleteTeacher(Integer id);

    boolean isEmployeeIdExists(String employeeId);
}
