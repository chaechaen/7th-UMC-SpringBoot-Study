package com.example.spring_study_hospital.domain.hospital.domain.repository;

import com.example.spring_study_hospital.domain.hospital.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    boolean existsByName(String name); // 병원 이름으로 존재 여부 확인

    Optional<Hospital> findByName(String name);
}
