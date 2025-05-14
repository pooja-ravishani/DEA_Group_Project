package com.ManagementApplication.StudentManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentDTO {

    private Integer id;

    @NotNull(message = "User ID is required")
    private Integer userId;

    private UserDTO user;

    @NotBlank(message = "Student ID is required")
    private String studentId;

    private String contactNumber;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private String address;

    @NotNull(message = "Enrollment year is required")
    private Integer enrollmentYear;

    private String department;

    private String programOfStudy;

    private String currentSemester;

    public Integer getUserId() {
        return 0;
    }
}
