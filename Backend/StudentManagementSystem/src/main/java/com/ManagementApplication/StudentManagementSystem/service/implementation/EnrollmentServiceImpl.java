package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.studentmanagement.StudentManagementSystem.dto.EnrollmentDTO;
import com.studentmanagement.StudentManagementSystem.entity.Course;
import com.studentmanagement.StudentManagementSystem.entity.Enrollment;
import com.studentmanagement.StudentManagementSystem.entity.Student;
import com.studentmanagement.StudentManagementSystem.exception.ResourceNotFoundException;
import com.studentmanagement.StudentManagementSystem.repository.CourseRepository;
import com.studentmanagement.StudentManagementSystem.repository.EnrollmentRepository;
import com.studentmanagement.StudentManagementSystem.repository.StudentRepository;
import com.studentmanagement.StudentManagementSystem.service.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository,
                                 ModelMapper modelMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        return modelMapper.map(enrollment, EnrollmentDTO.class);
    }

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = modelMapper.map(enrollmentDTO, Enrollment.class);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return modelMapper.map(savedEnrollment, EnrollmentDTO.class);
    }

    @Override
    public EnrollmentDTO updateEnrollment(Integer id, EnrollmentDTO enrollmentDTO) {
        Enrollment existingEnrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));

        modelMapper.map(enrollmentDTO, existingEnrollment);
        existingEnrollment.setId(id); // Ensure ID is preserved

        Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);
        return modelMapper.map(updatedEnrollment, EnrollmentDTO.class);
    }

    @Override
    public void deleteEnrollment(Integer id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return enrollmentRepository.findByStudent(student).stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByCourseId(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return enrollmentRepository.findByCourse(course).stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByStudentIdAndSemester(Integer studentId, String semester, String academicYear) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return enrollmentRepository.findByStudentAndSemesterAndAcademicYear(student, semester, academicYear).stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByCourseIdAndSemester(Integer courseId, String semester, String academicYear) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return enrollmentRepository.findByCourseAndSemesterAndAcademicYear(course, semester, academicYear).stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByStatus(String status) {
        return enrollmentRepository.findByEnrollmentStatus(status).stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO enrollStudentInCourse(Integer studentId, Integer courseId, String semester, String academicYear) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        if (isStudentEnrolledInCourse(studentId, courseId, semester, academicYear)) {
            throw new IllegalStateException("Student is already enrolled in this course for the specified semester and academic year");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setSemester(semester);
        enrollment.setAcademicYear(academicYear);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setEnrollmentStatus("ACTIVE");

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return modelMapper.map(savedEnrollment, EnrollmentDTO.class);
    }

    @Override
    public EnrollmentDTO withdrawStudentFromCourse(Integer enrollmentId, LocalDateTime withdrawDate) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        enrollment.setEnrollmentStatus("WITHDRAWN");
        enrollment.setWithdrawDate(withdrawDate);

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return modelMapper.map(updatedEnrollment, EnrollmentDTO.class);
    }

    @Override
    public boolean isStudentEnrolledInCourse(Integer studentId, Integer courseId, String semester, String academicYear) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        return enrollmentRepository.existsByStudentAndCourseAndSemesterAndAcademicYear(student, course, semester, academicYear);
    }
}
