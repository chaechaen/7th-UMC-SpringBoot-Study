package com.example.spring_study_hospital.domain.patient.dto;

import com.example.spring_study_hospital.domain.patient.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PatientRequestDTO {

    private String name; // 환자 이름
    private Integer age; // 환자 나이
    private Gender gender; // 환자 성별 (예: Male, Female)
    private String hospitalName; // 병원 이름
    private String departmentName; // 진료과 이름
    private String doctorName; // 의사 이름
    private LocalDateTime reservationDate; // 예약 날짜 및 시간
    private Long price; // 가격
}
