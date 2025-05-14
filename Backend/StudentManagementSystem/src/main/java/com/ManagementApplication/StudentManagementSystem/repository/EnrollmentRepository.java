package com.ManagementApplication.StudentManagementSystem.repository;

import com.studentmanagement.StudentManagementSystem.entity.Course;
import com.studentmanagement.StudentManagementSystem.entity.Enrollment;
import com.studentmanagement.StudentManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    List<Enrollment> findByStudent(Student student);

    List<Enrollment> findByCourse(Course course);

    List<Enrollment> findByStudentAndSemesterAndAcademicYear(Student student, String semester, String academicYear);

    List<Enrollment> findByCourseAndSemesterAndAcademicYear(Course course, String semester, String academicYear);

    List<Enrollment> findBySemesterAndAcademicYear(String semester, String academicYear);

    List<Enrollment> findByEnrollmentStatus(String enrollmentStatus);

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    boolean existsByStudentAndCourseAndSemesterAndAcademicYear(Student student, Course course, String semester, String academicYear);
}

