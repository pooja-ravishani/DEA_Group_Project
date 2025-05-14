package com.ManagementApplication.StudentManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttendanceDTO {

    private Integer id;

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    private StudentDTO student;

    @NotNull(message = "Course ID is required")
    private Integer courseId;

    private CourseDTO course;

    @NotNull(message = "Attendance date is required")
    private LocalDate attendanceDate;

    @NotBlank(message = "Status is required")
    private String status;

    private String remarks;

    private LocalDateTime recordedAt;

    @NotNull(message = "Recorded by ID is required")
    private Integer recordedById;

    private UserDTO recordedBy;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

