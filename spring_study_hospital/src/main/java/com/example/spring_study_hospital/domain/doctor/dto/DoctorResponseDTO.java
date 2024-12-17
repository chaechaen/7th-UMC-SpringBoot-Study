package com.example.spring_study_hospital.domain.doctor.dto;

import com.example.spring_study_hospital.domain.department.dto.DepartmentResponseDTO;
import com.example.spring_study_hospital.domain.doctor.domain.Doctor;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DoctorResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DoctorViewResponseDTO { // 의사 조회

        private Long doctorId;
        private String name;
        private Long career;
        private DepartmentResponseDTO.DepartmentForResponseDTO department;
        private HospitalResponseDTO.HospitalForResponseDTO hospital;

        public static DoctorViewResponseDTO from(Doctor doctor) {
            return DoctorViewResponseDTO.builder()
                    .doctorId(doctor.getId())
                    .name(doctor.getName())
                    .career(doctor.getCareer())
                    .department(DepartmentResponseDTO.DepartmentForResponseDTO.from(doctor.getDepartment()))
                    .hospital(HospitalResponseDTO.HospitalForResponseDTO.from(doctor.getDepartment().getHospital()))
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DoctorCreateResponseDTO { // 의사 등록
        private Long doctorId;
        private String name;
        private Long career;
        private DepartmentResponseDTO.DepartmentForResponseDTO department;
        private HospitalResponseDTO.HospitalForResponseDTO hospital;

        public static DoctorCreateResponseDTO from(Doctor doctor) {
            return DoctorCreateResponseDTO.builder()
                    .doctorId(doctor.getId())
                    .name(doctor.getName())
                    .career(doctor.getCareer())
                    .department(DepartmentResponseDTO.DepartmentForResponseDTO.from(doctor.getDepartment()))
                    .hospital(HospitalResponseDTO.HospitalForResponseDTO.from(doctor.getDepartment().getHospital()))
                    .build();
        }
    }
}
