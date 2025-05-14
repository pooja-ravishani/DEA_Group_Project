package com.ManagementApplication.StudentManagementSystem.controller;


import com.studentmanagement.StudentManagementSystem.dto.AttendanceDTO;
import com.studentmanagement.StudentManagementSystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable Integer id) {
        AttendanceDTO attendance = attendanceService.getAttendanceById(id);
        return attendance != null ? ResponseEntity.ok(attendance) : ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByStudentId(@PathVariable Integer studentId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByStudentId(studentId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByCourseId(@PathVariable Integer courseId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseId(courseId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByDate(date);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/course/{courseId}/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByCourseAndDate(
            @PathVariable Integer courseId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseIdAndDateRange(courseId, date, date);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByStudentAndCourse(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/student/{studentId}/course/{courseId}/daterange")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByDateRange(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByStudentIdAndDateRange(studentId, startDate, endDate);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByStatus(@PathVariable String status) {
        // This endpoint needs to be implemented in the service layer
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO createdAttendance = attendanceService.createAttendance(attendanceDTO);
        return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable Integer id, @Valid @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO updatedAttendance = attendanceService.updateAttendance(id, attendanceDTO);
        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
