package com.example.spring_study_hospital.domain.hospital.dto;

import com.example.spring_study_hospital.domain.department.domain.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HospitalRequestDTO {

    private String name;

    private String address;
}
