package com.ManagementApplication.StudentManagementSystem.entity;

import com.ManagementApplication.StudentManagementSystem.dto.AttendanceDto;
import com.ManagementApplication.StudentManagementSystem.dto.StudentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate date;
    private String status; // PRESENT, ABSENT, LATE
    private String remarks;

    public Long getId() {
        return null;
    }

    public Object getDate() {
        return null;
    }

    public String getStatus() {
        return "";
    }

    public String getRemarks() {
        return "";
    }

    public StudentDto getStudent() {
        return null;
    }

    public AttendanceDto getCourse() {
        return null;
    }

    public void setId(Long id) {
    }

    public void setDate(LocalDate date) {
    }

    public void setStatus(String status) {
    }

    public void setRemarks(String remarks) {
    }
}
