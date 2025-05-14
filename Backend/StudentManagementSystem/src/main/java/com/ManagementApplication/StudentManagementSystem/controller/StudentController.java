package com.ManagementApplication.StudentManagementSystem.console;

import com.ManagementApplication.StudentManagementSystem.dto.StudentDTO;
import com.ManagementApplication.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) {
        StudentDTO student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/academicTerm/{academicTerm}")
    public ResponseEntity<List<StudentDTO>> getStudentsByAcademicTerm(@PathVariable String academicTerm) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Map<String, Object>> getStudentCourses(@PathVariable Integer id) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{id}/attendance")
    public ResponseEntity<Map<String, Object>> getStudentAttendance(
            @PathVariable Integer id,
            @RequestParam(required = false) String academicTerm) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> partialUpdateStudent(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> updates) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Map<String, Object>> enrollStudentInCourse(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public ResponseEntity<Void> dropStudentFromCourse(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
