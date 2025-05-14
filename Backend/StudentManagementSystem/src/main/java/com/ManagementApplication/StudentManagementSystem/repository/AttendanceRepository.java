package com.ManagementApplication.StudentManagementSystem.repository;

import com.studentmanagement.StudentManagementSystem.entity.Attendance;
import com.studentmanagement.StudentManagementSystem.entity.Course;
import com.studentmanagement.StudentManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByStudent(Student student);

    List<Attendance> findByCourse(Course course);

    List<Attendance> findByStudentAndCourse(Student student, Course course);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    List<Attendance> findByStudentAndAttendanceDateBetween(Student student, LocalDate startDate, LocalDate endDate);

    List<Attendance> findByCourseAndAttendanceDateBetween(Course course, LocalDate startDate, LocalDate endDate);

    Optional<Attendance> findByStudentAndCourseAndAttendanceDate(Student student, Course course, LocalDate attendanceDate);

    List<Attendance> findByStatus(String status);

    // Additional methods for direct ID queries
    List<Attendance> findByStudentId(Integer studentId);

    List<Attendance> findByCourseId(Integer courseId);

    List<Attendance> findByStudentIdAndCourseId(Integer studentId, Integer courseId);

    List<Attendance> findByStudentIdAndAttendanceDateBetween(Integer studentId, LocalDate startDate, LocalDate endDate);

    List<Attendance> findByCourseIdAndAttendanceDateBetween(Integer courseId, LocalDate startDate, LocalDate endDate);
}
