package com.ManagementApplication.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;

    private String department;

    private String specialization;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "office_location")
    private String officeLocation;

    @Column(name = "office_hours")
    private String officeHours;

    public void setId(Integer id) {
    }

    public void setUser(User user) {
    }
}
