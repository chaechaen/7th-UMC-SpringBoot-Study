package com.example.spring_study_hospital.domain.reservation.dto;

import com.example.spring_study_hospital.domain.department.dto.DepartmentResponseDTO;
import com.example.spring_study_hospital.domain.doctor.dto.DoctorResponseDTO;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalResponseDTO;
import com.example.spring_study_hospital.domain.patient.dto.PatientResponseDTO;
import com.example.spring_study_hospital.domain.reservation.domain.Reservation;
import com.example.spring_study_hospital.domain.reservation.domain.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReservationResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReservationViewResponseDTO {

        private Long reservationId;
        private LocalDateTime reservationDate;
        private ReservationStatus status;
        private Long price;
        private String patientName;

        public static ReservationViewResponseDTO from(Reservation reservation) {
            return ReservationViewResponseDTO.builder()
                    .reservationId(reservation.getId())
                    .reservationDate(reservation.getReservationDate())
                    .status(reservation.getStatus())
                    .price(reservation.getPrice())
                    .patientName(reservation.getPatient().getName())
                    .build();
        }
    }
}

