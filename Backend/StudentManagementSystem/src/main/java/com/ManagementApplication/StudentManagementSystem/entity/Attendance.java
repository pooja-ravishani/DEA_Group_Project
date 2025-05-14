package com.ManagementApplication.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Column(nullable = false)
    private String status;

    private String remarks;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @ManyToOne
    @JoinColumn(name = "recorded_by_id", nullable = false)
    private User recordedBy;

    @Column(name = "recorded_by_id", insertable = false, updatable = false)
    private Integer recordedById;

    public void setRecordedAt(LocalDateTime now) {
    }

    public void setId(Integer id) {
    }

    public void setCourseId(Integer courseId) {
    }

    public void setAttendanceDate(LocalDate date) {
    }

    public void setRecordedById(Integer recordedById) {

    }
}

