package com.ManagementApplication.StudentManagementSystem.controller;

import com.ManagementApplication.StudentManagementSystem.dto.TeacherDTO;
import com.ManagementApplication.StudentManagementSystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        TeacherDTO teacher = teacherService.getTeacherById(id);
        return teacher != null ? ResponseEntity.ok(teacher) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherDTO>> searchTeachers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<TeacherDTO>> getTeachersByDepartment(@PathVariable String department) {
        List<TeacherDTO> teachers = teacherService.getTeachersByDepartment(department);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TeacherDTO>> getTeachersByCourse(@PathVariable Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Map<String, Object>> getTeacherCourses(@PathVariable Integer id) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Map<String, Object>> getTeacherStudents(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<Map<String, Object>> getTeacherSchedule(
            @PathVariable Integer id,
            @RequestParam(required = false) String academicTerm) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(
            @PathVariable Integer id,
            @Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok(updatedTeacher);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDTO> partialUpdateTeacher(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> updates) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teacherId}/assign/{courseId}")
    public ResponseEntity<Map<String, Object>> assignTeacherToCourse(
            @PathVariable Integer teacherId,
            @PathVariable Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping("/{teacherId}/unassign/{courseId}")
    public ResponseEntity<Void> unassignTeacherFromCourse(
            @PathVariable Integer teacherId,
            @PathVariable Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
