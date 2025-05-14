package com.ManagementApplication.StudentManagementSystem.repository;

import com.ManagementApplication.StudentManagementSystem.entity.Student;
import com.ManagementApplication.StudentManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByStudentId(String studentId);

    Optional<Student> findByUser(User user);

    List<Student> findByDepartment(String department);

    List<Student> findByEnrollmentYear(Integer enrollmentYear);

    List<Student> findByCurrentSemester(String currentSemester);

    boolean existsByStudentId(String studentId);
}
