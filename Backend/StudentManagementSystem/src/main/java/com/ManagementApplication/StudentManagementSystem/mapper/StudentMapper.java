package com.ManagementApplication.StudentManagementSystem.mapper;

import com.ManagementApplication.StudentManagementSystem.dto.StudentDto;
import com.ManagementApplication.StudentManagementSystem.entity.Student;

public class StudentMapper {

    public static StudentDto studentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public static Student student(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        return student;
    }
}
