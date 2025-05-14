package com.ManagementApplication.StudentManagementSystem.service;

import com.ManagementApplication.StudentManagementSystem.dto.GradeDTO;

import java.util.List;

import java.util.Map;

public interface GradeService {

    GradeDTO createGrade(GradeDTO gradeDTO);

    GradeDTO getGradeById(Integer id);

    List<GradeDTO> getAllGrades();

    List<GradeDTO> getGradesByStudentId(Integer studentId);

    List<GradeDTO> getGradesByCourseId(Integer courseId);

    List<GradeDTO> getGradesByStudentIdAndCourseId(Integer studentId, Integer courseId);

    List<GradeDTO> getGradesByAcademicTerm(String academicTerm);

    List<GradeDTO> getGradesByStudentIdAndAcademicTerm(Integer studentId, String academicTerm);

    List<GradeDTO> getGradesByCourseIdAndAcademicTerm(Integer courseId, String academicTerm);

    List<GradeDTO> getGradesByGradeType(String gradeType);

    Map<String, Object> getStudentCourseGradesSummary(Integer studentId, Integer courseId);

    GradeDTO updateGrade(Integer id, GradeDTO gradeDTO);

    void deleteGrade(Integer id);

    List<GradeDTO> createBulkGrades(List<GradeDTO> gradeDTOs);
}

