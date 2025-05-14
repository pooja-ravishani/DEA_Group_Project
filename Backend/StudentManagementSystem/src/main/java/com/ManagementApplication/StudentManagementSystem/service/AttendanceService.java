package com.ManagementApplication.StudentManagementSystem.service;

import com.studentmanagement.StudentManagementSystem.dto.AttendanceDTO;

import java.time.LocalDate;

import java.util.List;

import java.util.Map;

public interface AttendanceService {

    AttendanceDTO createAttendance(AttendanceDTO attendanceDTO);

    AttendanceDTO getAttendanceById(Integer id);

    List<AttendanceDTO> getAllAttendance();

    List<AttendanceDTO> getAttendanceByStudentId(Integer studentId);

    List<AttendanceDTO> getAttendanceByStudentIdAndCourseId(Integer studentId, Integer courseId);

    List<AttendanceDTO> getAttendanceByCourseId(Integer courseId);

    List<AttendanceDTO> getAttendanceByDate(LocalDate date);

    List<AttendanceDTO> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate);

    List<AttendanceDTO> getAttendanceByStudentIdAndDateRange(Integer studentId, LocalDate startDate, LocalDate endDate);

    List<AttendanceDTO> getAttendanceByCourseIdAndDateRange(Integer courseId, LocalDate startDate, LocalDate endDate);

    Map<String, Double> getAttendanceStatsByStudentId(Integer studentId, Integer courseId);

    AttendanceDTO updateAttendance(Integer id, AttendanceDTO attendanceDTO);

    void deleteAttendance(Integer id);

    List<AttendanceDTO> markBulkAttendance(Integer courseId, LocalDate date, List<AttendanceDTO> attendanceDTOs, Integer recordedById);
}

