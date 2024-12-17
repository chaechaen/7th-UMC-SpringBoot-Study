package com.example.spring_study_hospital.domain.patient.dto;

import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.department.dto.DepartmentResponseDTO;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalResponseDTO;
import com.example.spring_study_hospital.domain.patient.domain.Gender;
import com.example.spring_study_hospital.domain.patient.domain.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PatientResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatientViewResponseDTO {
        private Long patientId;
        private String name;
        private Integer age;
        private Gender gender;
        private DepartmentResponseDTO.DepartmentForResponseDTO department;
        private HospitalResponseDTO.HospitalForResponseDTO hospital;

        public static PatientViewResponseDTO from(Patient patient, Department department) {
            return PatientViewResponseDTO.builder()
                    .patientId(patient.getId())
                    .name(patient.getName())
                    .age(patient.getAge())
                    .gender(patient.getGender())
                    .department(DepartmentResponseDTO.DepartmentForResponseDTO.from(department))
                    .hospital(HospitalResponseDTO.HospitalForResponseDTO.from(department.getHospital()))
                    .build();
        }
    }
}