package com.example.spring_study_hospital.domain.patient.domain.repository;

import com.example.spring_study_hospital.domain.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT DISTINCT p FROM Patient p " +
            "JOIN p.reservation r " +
            "JOIN r.doctor d " +
            "JOIN d.department dept " +
            "JOIN dept.hospital h " +
            "WHERE h.name = :hospitalName " +
            "AND dept.name = :departmentName")
    List<Patient> findAllByHospitalNameAndDepartmentName(
            @Param("hospitalName") String hospitalName,
            @Param("departmentName") String departmentName
    );

    Optional<Patient> findByName(String name);
}