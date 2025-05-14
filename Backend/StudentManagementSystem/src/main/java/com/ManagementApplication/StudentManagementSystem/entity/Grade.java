package com.ManagementApplication.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "academic_term", nullable = false)
    private String academicTerm;

    @Column(name = "grade_type", nullable = false)
    private String gradeType;

    private String assignment;

    private Float score;

    @Column(name = "letter_grade")
    private String letterGrade;

    @Column(name = "graded_at", nullable = false)
    private LocalDateTime gradedAt;

    @ManyToOne
    @JoinColumn(name = "graded_by_id", nullable = false)
    private User gradedBy;

    private String remarks;

    public void setGradedAt(LocalDateTime gradedAt) {
        this.gradedAt = gradedAt;
    }

    public LocalDateTime getGradedAt() {
        return gradedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Object getGradeType() {
        return null;
    }
}

