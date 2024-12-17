package com.example.spring_study_hospital.domain.department.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByHospitalId(Long hospitalId);
    boolean existsByHospitalName(String name);

    @Query("SELECT d FROM Department d " +
            "JOIN FETCH d.hospital h " +
            "WHERE h.name = :hospitalName " +
            "AND d.name = :departmentName")
    Optional<Department> findByHospitalNameAndDepartmentName(
            @Param("hospitalName") String hospitalName,
            @Param("departmentName") String departmentName
    );


    // 특정 병원의 특정 진료과 찾기
    @Query("SELECT EXISTS(SELECT 1 FROM Department d WHERE d.hospital.name = :hospitalName AND d.name = :departmentName)")
    boolean existsByHospitalNameAndName(
            @Param("hospitalName") String hospitalName,
            @Param("departmentName") String departmentName);

    // 병원 ID와 진료과 이름으로 해당 진료과 찾기
    Optional<Department> findByHospitalIdAndName(Long hospitalId, String name);
}
