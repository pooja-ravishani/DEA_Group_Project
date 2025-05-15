package com.ManagementApplication.StudentManagementSystem.repository;

import com.studentmanagement.StudentManagementSystem.entity.Teacher;
import com.studentmanagement.StudentManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findByEmployeeId(String employeeId);

    Optional<Teacher> findByUser(User user);

    List<Teacher> findByDepartment(String department);

    List<Teacher> findBySpecialization(String specialization);

    boolean existsByEmployeeId(String employeeId);
}
