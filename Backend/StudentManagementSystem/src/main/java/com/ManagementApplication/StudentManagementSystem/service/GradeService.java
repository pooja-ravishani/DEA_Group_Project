package com.ManagementApplication.StudentManagementSystem.service;

import com.ManagementApplication.StudentManagementSystem.dto.GradeDto;
import com.ManagementApplication.StudentManagementSystem.exception.ResourceNotFoundException;
import java.util.List;

public interface GradeService {
    List<GradeDto> getAllGrades();

    GradeDto getGradeById(Long id) throws ResourceNotFoundException;

    GradeDto createGrade(GradeDto gradeDto);

    GradeDto updateGradeById(Long id, GradeDto gradeDto) throws ResourceNotFoundException;

    void deleteGradeById(Long id) throws ResourceNotFoundException;

    List<GradeDto> getGradesByStudentId(Long studentId);

    List<GradeDto> getGradesByCourseId(Long courseId);

    List<GradeDto> getGradesByStudentIdAndCourseId(Long studentId, Long courseId);
}
