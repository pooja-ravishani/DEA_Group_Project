package com.ManagementApplication.StudentManagementSystem.controller;

import com.ManagementApplication.StudentManagementSystem.dto.GradeDTO;
import com.ManagementApplication.StudentManagementSystem.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Integer id) {
        GradeDTO grade = gradeService.getGradeById(id);
        return grade != null ? ResponseEntity.ok(grade) : ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeDTO>> getGradesByStudentId(@PathVariable Integer studentId) {
        List<GradeDTO> grades = gradeService.getGradesByStudentId(studentId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<GradeDTO>> getGradesByCourseId(@PathVariable Integer courseId) {
        List<GradeDTO> grades = gradeService.getGradesByCourseId(courseId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<GradeDTO>> getGradesByStudentAndCourse(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        List<GradeDTO> grades = gradeService.getGradesByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/academicTerm/{academicTerm}")
    public ResponseEntity<List<GradeDTO>> getGradesByAcademicTerm(@PathVariable String academicTerm) {
        List<GradeDTO> grades = gradeService.getGradesByAcademicTerm(academicTerm);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/student/{studentId}/course/{courseId}/summary")
    public ResponseEntity<Map<String, Object>> getStudentCourseGradesSummary(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        Map<String, Object> summary = gradeService.getStudentCourseGradesSummary(studentId, courseId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/student/{studentId}/academicTerm/{academicTerm}")
    public ResponseEntity<List<GradeDTO>> getGradesByStudentIdAndAcademicTerm(
            @PathVariable Integer studentId,
            @PathVariable String academicTerm) {
        List<GradeDTO> grades = gradeService.getGradesByStudentIdAndAcademicTerm(studentId, academicTerm);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/course/{courseId}/academicTerm/{academicTerm}")
    public ResponseEntity<List<GradeDTO>> getGradesByCourseIdAndAcademicTerm(
            @PathVariable Integer courseId,
            @PathVariable String academicTerm) {
        List<GradeDTO> grades = gradeService.getGradesByCourseIdAndAcademicTerm(courseId, academicTerm);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/type/{gradeType}")
    public ResponseEntity<List<GradeDTO>> getGradesByGradeType(@PathVariable String gradeType) {
        List<GradeDTO> grades = gradeService.getGradesByGradeType(gradeType);
        return ResponseEntity.ok(grades);
    }

    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(@Valid @RequestBody GradeDTO gradeDTO) {
        GradeDTO createdGrade = gradeService.createGrade(gradeDTO);
        return new ResponseEntity<>(createdGrade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Integer id, @Valid @RequestBody GradeDTO gradeDTO) {
        GradeDTO updatedGrade = gradeService.updateGrade(id, gradeDTO);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Integer id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
