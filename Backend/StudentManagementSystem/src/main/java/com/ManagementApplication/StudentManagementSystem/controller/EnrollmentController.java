package com.ManagementApplication.StudentManagementSystem.controller;

import com.studentmanagement.StudentManagementSystem.dto.EnrollmentDTO;
import com.studentmanagement.StudentManagementSystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Integer id) {
        EnrollmentDTO enrollment = enrollmentService.getEnrollmentById(id);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudentId(@PathVariable Integer studentId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourseId(@PathVariable Integer courseId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}/semester/{semester}/academic-year/{academicYear}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudentAndSemester(
            @PathVariable Integer studentId,
            @PathVariable String semester,
            @PathVariable String academicYear) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByStudentIdAndSemester(studentId, semester, academicYear);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}/semester/{semester}/academic-year/{academicYear}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourseAndSemester(
            @PathVariable Integer courseId,
            @PathVariable String semester,
            @PathVariable String academicYear) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByCourseIdAndSemester(courseId, semester, academicYear);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStatus(@PathVariable String status) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByStatus(status);
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Integer id, @Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO updatedEnrollment = enrollmentService.updateEnrollment(id, enrollmentDTO);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
