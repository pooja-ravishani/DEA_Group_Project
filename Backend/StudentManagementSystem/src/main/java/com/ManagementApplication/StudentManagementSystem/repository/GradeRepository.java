package com.ManagementApplication.StudentManagementSystem.repository;

import com.ManagementApplication.StudentManagementSystem.entity.Course;
import com.ManagementApplication.StudentManagementSystem.entity.Grade;
import com.ManagementApplication.StudentManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByStudent(Student student);

    List<Grade> findByCourse(Course course);

    List<Grade> findByStudentAndCourse(Student student, Course course);

    List<Grade> findByStudentAndAcademicTerm(Student student, String academicTerm);

    List<Grade> findByCourseAndAcademicTerm(Course course, String academicTerm);

    List<Grade> findByGradeType(String gradeType);

    List<Grade> findByAssignment(String assignment);

    List<Grade> findByLetterGrade(String letterGrade);

    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.student = ?1 AND g.course = ?2")
    Float findAverageScoreByStudentAndCourse(Student student, Course course);

    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.student = ?1 AND g.academicTerm = ?2")
    Float findAverageScoreByStudentAndAcademicTerm(Student student, String academicTerm);
}
