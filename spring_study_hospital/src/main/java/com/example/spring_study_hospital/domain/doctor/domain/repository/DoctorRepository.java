package com.example.spring_study_hospital.domain.doctor.domain.repository;

import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.doctor.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // 특정 병원의 모든 의사 조회
    @Query("SELECT d FROM Doctor d JOIN FETCH d.department dept WHERE dept.hospital.id = :hospitalId")
    List<Doctor> findAllByHospitalId(@Param("hospitalId") Long hospitalId);

    Optional<Doctor> findByNameAndDepartment(String doctorName, Department department);
}
