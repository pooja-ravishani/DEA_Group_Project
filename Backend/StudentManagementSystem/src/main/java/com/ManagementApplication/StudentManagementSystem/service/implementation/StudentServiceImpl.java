package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.ManagementApplication.StudentManagementSystem.dto.StudentDTO;
import com.ManagementApplication.StudentManagementSystem.entity.Student;
import com.ManagementApplication.StudentManagementSystem.entity.User;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import com.ManagementApplication.StudentManagementSystem.repository.StudentRepository;
import com.ManagementApplication.StudentManagementSystem.repository.UserRepository;
import com.ManagementApplication.StudentManagementSystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        User user = userRepository.findById(studentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + studentDTO.getUserId()));

        Student student = modelMapper.map(studentDTO, Student.class);
        student.setUser(user);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentByStudentId(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with student id: " + studentId));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with user id: " + userId));

        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByDepartment(String department) {
        List<Student> students = studentRepository.findByDepartment(department);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByEnrollmentYear(Integer enrollmentYear) {
        List<Student> students = studentRepository.findByEnrollmentYear(enrollmentYear);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByCurrentSemester(String currentSemester) {
        List<Student> students = studentRepository.findByCurrentSemester(currentSemester);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }

        User user = userRepository.findById(studentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + studentDTO.getUserId()));

        Student student = modelMapper.map(studentDTO, Student.class);
        student.setId(id);
        student.setUser(user);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    @Override
    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public boolean isStudentIdExists(String studentId) {
        return studentRepository.existsByStudentId(studentId);
    }
}
