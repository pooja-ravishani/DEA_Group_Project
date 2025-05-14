package com.ManagementApplication.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "student_id", unique = true, nullable = false)
    private String studentId;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String address;

    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    private String department;

    @Column(name = "program_of_study")
    private String programOfStudy;

    @Column(name = "current_semester")
    private String currentSemester;

    public void setUser(User user) {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
