package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.ManagementApplication.StudentManagementSystem.dto.GradeDto;
import com.ManagementApplication.StudentManagementSystem.entity.Course;
import com.ManagementApplication.StudentManagementSystem.entity.Grade;
import com.ManagementApplication.StudentManagementSystem.entity.Student;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import com.ManagementApplication.StudentManagementSystem.mapper.GradeMapper;
import com.ManagementApplication.StudentManagementSystem.repository.CourseRepository;
import com.ManagementApplication.StudentManagementSystem.repository.GradeRepository;
import com.ManagementApplication.StudentManagementSystem.repository.StudentRepository;
import com.ManagementApplication.StudentManagementSystem.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDto> getAllGrades() {
        return gradeRepository.findAllWithRelationships().stream()
                .map(GradeMapper::gradeDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GradeDto getGradeById(Long id) {
        Grade grade = gradeRepository.findByIdWithRelationships(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + id));
        return GradeMapper.gradeDto(grade);
    }

    @Override
    @Transactional
    public GradeDto createGrade(GradeDto gradeDto) {
        Grade grade = GradeMapper.grade(gradeDto);

        Student student = studentRepository.findById(gradeDto.getStudentId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Student not found with id: " + gradeDto.getStudentId()));
        grade.setStudent(student);

        Course course = courseRepository.findById(gradeDto.getCourseId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Course not found with id: " + gradeDto.getCourseId()));
        grade.setCourse(course);

        return GradeMapper.gradeDto(gradeRepository.save(grade));
    }

    @Override
    @Transactional
    public GradeDto updateGradeById(Long id, GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + id));

        grade.setGradeValue(gradeDto.getGradeValue());
        grade.setComments(gradeDto.getComments());

        if (gradeDto.getStudentId() != null) {
            Student student = studentRepository.findById(gradeDto.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Student not found with id: " + gradeDto.getStudentId()));
            grade.setStudent(student);
        }

        if (gradeDto.getCourseId() != null) {
            Course course = courseRepository.findById(gradeDto.getCourseId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Course not found with id: " + gradeDto.getCourseId()));
            grade.setCourse(course);
        }

        return GradeMapper.gradeDto(gradeRepository.save(grade));
    }

    @Override
    @Transactional
    public void deleteGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + id));
        gradeRepository.delete(grade);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDto> getGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId).stream()
                .map(GradeMapper::gradeDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDto> getGradesByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId).stream()
                .map(GradeMapper::gradeDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDto> getGradesByStudentIdAndCourseId(Long studentId, Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId).stream()
                .map(GradeMapper::gradeDto)
                .collect(Collectors.toList());
    }
}
