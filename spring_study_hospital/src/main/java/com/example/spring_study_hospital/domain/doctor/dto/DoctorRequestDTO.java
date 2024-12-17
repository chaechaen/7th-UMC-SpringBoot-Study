package com.example.spring_study_hospital.domain.doctor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DoctorRequestDTO {

    private Long career; // 경력 (년수)

    private String name;

    public String departmentName;

    public String hospitalName;
}
