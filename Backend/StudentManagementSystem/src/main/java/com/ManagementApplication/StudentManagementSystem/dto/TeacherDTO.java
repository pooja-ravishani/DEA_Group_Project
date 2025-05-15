package com.ManagementApplication.StudentManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherDTO {

    private Integer id;

    @NotNull(message = "User ID is required")
    private Integer userId;

    private UserDTO user;

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    private String department;

    private String specialization;

    private String contactNumber;

    private String officeLocation;

    private String officeHours;

    public Integer getUserId() {
        return 0;
    }
}
