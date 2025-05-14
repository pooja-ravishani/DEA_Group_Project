package com.ManagementApplication.StudentManagementSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CourseDTO {

    private Integer id;

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    private String courseName;

    private String description;

    @NotNull(message = "Credit hours is required")
    @Min(value = 1, message = "Credit hours must be at least 1")
    private Integer creditHours;

    private String department;

    private Integer teacherId;

    private TeacherDTO teacher;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
