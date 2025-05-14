package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.ManagementApplication.StudentManagementSystem.dto.GradeDTO;
import com.ManagementApplication.StudentManagementSystem.entity.Course;
import com.ManagementApplication.StudentManagementSystem.entity.Grade;
import com.ManagementApplication.StudentManagementSystem.entity.Student;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import com.ManagementApplication.StudentManagementSystem.repository.CourseRepository;
import com.ManagementApplication.StudentManagementSystem.repository.GradeRepository;
import com.ManagementApplication.StudentManagementSystem.repository.StudentRepository;
import com.ManagementApplication.StudentManagementSystem.service.GradeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository,
                            StudentRepository studentRepository,
                            CourseRepository courseRepository,
                            ModelMapper modelMapper) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO getGradeById(Integer id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + id));
        return modelMapper.map(grade, GradeDTO.class);
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = modelMapper.map(gradeDTO, Grade.class);
        grade.setGradedAt(LocalDateTime.now());
        Grade savedGrade = gradeRepository.save(grade);
        return modelMapper.map(savedGrade, GradeDTO.class);
    }

    @Override
    @Transactional
    public List<GradeDTO> createBulkGrades(List<GradeDTO> gradeDTOs) {
        List<Grade> grades = gradeDTOs.stream()
                .map(dto -> {
                    Grade grade = modelMapper.map(dto, Grade.class);
                    grade.setGradedAt(LocalDateTime.now());
                    return grade;
                })
                .collect(Collectors.toList());

        List<Grade> savedGrades = gradeRepository.saveAll(grades);
        return savedGrades.stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO updateGrade(Integer id, GradeDTO gradeDTO) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + id));

        modelMapper.map(gradeDTO, existingGrade);
        existingGrade.setId(id); // Ensure ID is preserved
        existingGrade.setGradedAt(LocalDateTime.now());

        Grade updatedGrade = gradeRepository.save(existingGrade);
        return modelMapper.map(updatedGrade, GradeDTO.class);
    }

    @Override
    public void deleteGrade(Integer id) {
        if (!gradeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Grade not found with id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    @Override
    public List<GradeDTO> getGradesByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return gradeRepository.findByStudent(student).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByCourseId(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return gradeRepository.findByCourse(course).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return gradeRepository.findByStudentAndCourse(student, course).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByAcademicTerm(String academicTerm) {
        return gradeRepository.findByStudentAndAcademicTerm(null, academicTerm).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByStudentIdAndAcademicTerm(Integer studentId, String academicTerm) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return gradeRepository.findByStudentAndAcademicTerm(student, academicTerm).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByCourseIdAndAcademicTerm(Integer courseId, String academicTerm) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return gradeRepository.findByCourseAndAcademicTerm(course, academicTerm).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByGradeType(String gradeType) {
        return gradeRepository.findByGradeType(gradeType).stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getStudentCourseGradesSummary(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        List<Grade> grades = gradeRepository.findByStudentAndCourse(student, course);
        Float averageScore = gradeRepository.findAverageScoreByStudentAndCourse(student, course);

        Map<String, Object> summary = new HashMap<>();
        summary.put("studentId", studentId);
        summary.put("courseId", courseId);
        summary.put("grades", grades.stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList()));
        summary.put("averageScore", averageScore != null ? averageScore : 0.0);
        summary.put("totalAssignments", grades.size());
        summary.put("gradeTypes", grades.stream()
                .map(Grade::getGradeType)
                .distinct()
                .collect(Collectors.toList()));

        return summary;
    }
}
