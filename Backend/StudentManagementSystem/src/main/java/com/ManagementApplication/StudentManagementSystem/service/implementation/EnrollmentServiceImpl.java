package com.ManagementApplication.StudentManagementSystem.services.implementation;

import com.ManagementApplication.StudentManagementSystem.dto.EnrollmentDto;
import com.ManagementApplication.StudentManagementSystem.entity.Course;
import com.ManagementApplication.StudentManagementSystem.entity.Enrollment;
import com.ManagementApplication.StudentManagementSystem.entity.Student;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import com.ManagementApplication.StudentManagementSystem.mapper.EnrollmentMapper;
import com.ManagementApplication.StudentManagementSystem.repository.CourseRepository;
import com.ManagementApplication.StudentManagementSystem.repository.EnrollmentRepository;
import com.ManagementApplication.StudentManagementSystem.repository.StudentRepository;
import com.ManagementApplication.StudentManagementSystem.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentRepository.findAllWithRelationships().stream()
                .map(EnrollmentMapper::enrollmentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentDto getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findByIdWithRelationships(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        return EnrollmentMapper.enrollmentDto(enrollment);
    }

    @Override
    @Transactional
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = EnrollmentMapper.enrollment(enrollmentDto);

        Student student = studentRepository.findById(enrollmentDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + enrollmentDto.getStudentId()));
        enrollment.setStudent(student);

        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id: " + enrollmentDto.getCourseId()));
        enrollment.setCourse(course);

        return EnrollmentMapper.enrollmentDto(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional
    public EnrollmentDto updateEnrollmentById(Long id, EnrollmentDto enrollmentDto) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));

        enrollment.setEnrollmentDate(enrollmentDto.getEnrollmentDate());
        enrollment.setStatus(enrollmentDto.getStatus());
        enrollment.setSemester(enrollmentDto.getSemester());
        enrollment.setAcademicYear(enrollmentDto.getAcademicYear());

        if (enrollmentDto.getStudentId() != null) {
            Student student = studentRepository.findById(enrollmentDto.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Student not found with id: " + enrollmentDto.getStudentId()));
            enrollment.setStudent(student);
        }

        if (enrollmentDto.getCourseId() != null) {
            Course course = courseRepository.findById(enrollmentDto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Course not found with id: " + enrollmentDto.getCourseId()));
            enrollment.setCourse(course);
        }

        return EnrollmentMapper.enrollmentDto(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional
    public void deleteEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(EnrollmentMapper::enrollmentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDto> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId).stream()
                .map(EnrollmentMapper::enrollmentDto)
                .collect(Collectors.toList());
    }
}
