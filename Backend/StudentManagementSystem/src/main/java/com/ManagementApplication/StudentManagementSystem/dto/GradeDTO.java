package com.ManagementApplication.StudentManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GradeDTO {

    private Integer id;

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    private StudentDTO student;

    @NotNull(message = "Course ID is required")
    private Integer courseId;

    private CourseDTO course;

    @NotBlank(message = "Academic term is required")
    private String academicTerm;

    @NotBlank(message = "Grade type is required")
    private String gradeType;

    private String assignment;

    @PositiveOrZero(message = "Score must be zero or positive")
    private Float score;

    private String letterGrade;

    private LocalDateTime gradedAt;

    @NotNull(message = "Graded by ID is required")
    private Integer gradedById;

    private UserDTO gradedBy;

    private String remarks;
}

