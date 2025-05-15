package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.ManagementApplication.StudentManagementSystem.dto.TeacherDTO;
import com.ManagementApplication.StudentManagementSystem.entity.Teacher;
import com.ManagementApplication.StudentManagementSystem.entity.User;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import com.ManagementApplication.StudentManagementSystem.repository.TeacherRepository;
import com.ManagementApplication.StudentManagementSystem.repository.UserRepository;
import com.ManagementApplication.StudentManagementSystem.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        User user = userRepository.findById(teacherDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + teacherDTO.getUserId()));

        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
        teacher.setUser(user);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return modelMapper.map(savedTeacher, TeacherDTO.class);
    }

    @Override
    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public TeacherDTO getTeacherByEmployeeId(String employeeId) {
        Teacher teacher = teacherRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with employee id: " + employeeId));
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public TeacherDTO getTeacherByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Teacher teacher = teacherRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with user id: " + userId));

        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> getTeachersByDepartment(String department) {
        List<Teacher> teachers = teacherRepository.findByDepartment(department);
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> getTeachersBySpecialization(String specialization) {
        List<Teacher> teachers = teacherRepository.findBySpecialization(specialization);
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(Integer id, TeacherDTO teacherDTO) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with id: " + id);
        }

        User user = userRepository.findById(teacherDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + teacherDTO.getUserId()));

        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
        teacher.setId(id);
        teacher.setUser(user);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return modelMapper.map(updatedTeacher, TeacherDTO.class);
    }

    @Override
    public void deleteTeacher(Integer id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean isEmployeeIdExists(String employeeId) {
        return teacherRepository.existsByEmployeeId(employeeId);
    }
}
