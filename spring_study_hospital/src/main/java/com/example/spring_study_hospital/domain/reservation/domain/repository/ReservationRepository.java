package com.example.spring_study_hospital.domain.reservation.domain.repository;

import com.example.spring_study_hospital.domain.doctor.domain.Doctor;
import com.example.spring_study_hospital.domain.patient.domain.Patient;
import com.example.spring_study_hospital.domain.reservation.domain.Reservation;
import com.example.spring_study_hospital.domain.reservation.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 환자와 의사로 완료되지 않은 예약이 있는지 확인
    boolean existsByPatientAndDoctorAndStatusNot(
            Patient patient,
            Doctor doctor,
            ReservationStatus status
    );

    // 특정 병원의 특정 진료과의 모든 예약 조회
    @Query("SELECT DISTINCT r FROM Reservation r " +
            "JOIN FETCH r.patient p " +
            "JOIN FETCH r.doctor d " +
            "JOIN FETCH d.department dept " +
            "JOIN FETCH dept.hospital h " +
            "WHERE h.name = :hospitalName " +
            "AND dept.name = :departmentName")
    List<Reservation> findAllByHospitalNameAndDepartmentName(
            @Param("hospitalName") String hospitalName,
            @Param("departmentName") String departmentName);
}
