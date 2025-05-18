package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.ManagementApplication.StudentManagementSystem.dto.StudentDto;
import com.ManagementApplication.StudentManagementSystem.entity.Student;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import com.ManagementApplication.StudentManagementSystem.mapper.StudentMapper;
import com.ManagementApplication.StudentManagementSystem.repository.StudentRepository;
import com.ManagementApplication.StudentManagementSystem.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::studentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return StudentMapper.studentDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.student(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.studentDto(savedStudent);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        Student student = StudentMapper.student(studentDto);
        student.setId(id);
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.studentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
