package com.ManagementApplication.StudentManagementSystem.service.implementation;

import com.studentmanagement.StudentManagementSystem.dto.AttendanceDTO;
import com.studentmanagement.StudentManagementSystem.entity.Attendance;
import com.studentmanagement.StudentManagementSystem.exception.ResourceNotFoundException;
import com.studentmanagement.StudentManagementSystem.repository.AttendanceRepository;
import com.studentmanagement.StudentManagementSystem.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ModelMapper modelMapper) {
        this.attendanceRepository = attendanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll().stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDTO getAttendanceById(Integer id) {
        return attendanceRepository.findById(id)
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with id: " + id));
    }

    @Override
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = modelMapper.map(attendanceDTO, Attendance.class);
        attendance.setRecordedAt(LocalDateTime.now());
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return modelMapper.map(savedAttendance, AttendanceDTO.class);
    }

    @Override
    public AttendanceDTO updateAttendance(Integer id, AttendanceDTO attendanceDTO) {
        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with id: " + id));

        modelMapper.map(attendanceDTO, existingAttendance);
        existingAttendance.setId(id); // Ensure ID is preserved
        existingAttendance.setRecordedAt(LocalDateTime.now());

        Attendance updatedAttendance = attendanceRepository.save(existingAttendance);
        return modelMapper.map(updatedAttendance, AttendanceDTO.class);
    }

    @Override
    public void deleteAttendance(Integer id) {
        if (!attendanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Attendance record not found with id: " + id);
        }
        attendanceRepository.deleteById(id);
    }

    @Override
    public List<AttendanceDTO> getAttendanceByStudentId(Integer studentId) {
        return attendanceRepository.findByStudentId(studentId).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByCourseId(Integer courseId) {
        return attendanceRepository.findByCourseId(courseId).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByAttendanceDateBetween(startDate, endDate).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByStudentIdAndDateRange(Integer studentId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByStudentIdAndAttendanceDateBetween(studentId, startDate, endDate).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByCourseIdAndDateRange(Integer courseId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByCourseIdAndAttendanceDateBetween(courseId, startDate, endDate).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return attendanceRepository.findByStudentIdAndCourseId(studentId, courseId).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Double> getAttendanceStatsByStudentId(Integer studentId, Integer courseId) {
        List<AttendanceDTO> attendances = getAttendanceByStudentIdAndCourseId(studentId, courseId);
        Map<String, Double> stats = new HashMap<>();

        if (attendances.isEmpty()) {
            stats.put("attendanceRate", 0.0);
            stats.put("presentCount", 0.0);
            stats.put("absentCount", 0.0);
            stats.put("lateCount", 0.0);
            return stats;
        }

        long totalClasses = attendances.size();
        long presentCount = attendances.stream()
                .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus()))
                .count();
        long absentCount = attendances.stream()
                .filter(a -> "ABSENT".equalsIgnoreCase(a.getStatus()))
                .count();
        long lateCount = attendances.stream()
                .filter(a -> "LATE".equalsIgnoreCase(a.getStatus()))
                .count();

        stats.put("attendanceRate", (double) presentCount / totalClasses * 100);
        stats.put("presentCount", (double) presentCount);
        stats.put("absentCount", (double) absentCount);
        stats.put("lateCount", (double) lateCount);

        return stats;
    }

    @Override
    public List<AttendanceDTO> markBulkAttendance(Integer courseId, LocalDate date, List<AttendanceDTO> attendanceDTOs, Integer recordedById) {
        List<Attendance> attendances = attendanceDTOs.stream()
                .map(dto -> {
                    Attendance attendance = modelMapper.map(dto, Attendance.class);
                    attendance.setCourseId(courseId);
                    attendance.setAttendanceDate(date);
                    attendance.setRecordedById(recordedById);
                    attendance.setRecordedAt(LocalDateTime.now());
                    return attendance;
                })
                .collect(Collectors.toList());

        List<Attendance> savedAttendances = attendanceRepository.saveAll(attendances);
        return savedAttendances.stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }
}
