package com.example.spring_study_hospital.domain.reservation.dto;

import com.example.spring_study_hospital.domain.patient.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationRequestDTO {

    private String hospitalName;
    private String departmentName;
    private String doctorName;
    private String patientName;
    private LocalDateTime reservationDate;
    private Long price;
    private Integer patientAge;
    private Gender patientGender;
}
