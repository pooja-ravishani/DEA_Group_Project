package com.ManagementApplication.StudentManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnrollmentDTO {

    private Integer id;

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    private StudentDTO student;

    @NotNull(message = "Course ID is required")
    private Integer courseId;

    private CourseDTO course;

    @NotBlank(message = "Semester is required")
    private String semester;

    @NotBlank(message = "Academic year is required")
    private String academicYear;

    @NotBlank(message = "Enrollment status is required")
    private String enrollmentStatus;

    @NotNull(message = "Enrollment date is required")
    private LocalDateTime enrollmentDate;

    private LocalDateTime withdrawDate;
}
