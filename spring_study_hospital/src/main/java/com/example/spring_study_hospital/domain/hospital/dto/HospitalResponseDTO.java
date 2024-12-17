package com.example.spring_study_hospital.domain.hospital.dto;

import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.department.dto.DepartmentResponseDTO;
import com.example.spring_study_hospital.domain.doctor.domain.Doctor;
import com.example.spring_study_hospital.domain.doctor.dto.DoctorResponseDTO;
import com.example.spring_study_hospital.domain.hospital.domain.Hospital;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HospitalResponseDTO { // 병원 응답

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HospitalViewResponseDTO { // 병원 조회

        private Long hospitalId;
        private String name;
        private String address;
        private List<DepartmentResponseDTO.DepartmentForResponseDTO> departments; // 병원 조회시 진료과

        public static HospitalViewResponseDTO from(Hospital hospital) {
            return HospitalViewResponseDTO.builder()
                    .hospitalId(hospital.getId())
                    .name(hospital.getName())
                    .address(hospital.getAddress())
                    .departments(hospital.getDepartments().stream()  // List<Department>를 List<DepartmentForResponseDTO>로 변환
                            .map(DepartmentResponseDTO.DepartmentForResponseDTO::from)
                            .collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HospitalForResponseDTO { // 의사, 진료과 조회시 병원

        private Long hospitalId;
        private String hospitalName;

        public static HospitalForResponseDTO from(Hospital hospital) {
            return HospitalForResponseDTO.builder()
                    .hospitalId(hospital.getId())
                    .hospitalName(hospital.getName())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HospitalCreateResponseDTO { // 병원 등록

        private Long hospitalId;

        private String name;

        private String address;

        public static HospitalCreateResponseDTO from(Hospital hospital) {
            return HospitalResponseDTO.HospitalCreateResponseDTO.builder()
                    .hospitalId(hospital.getId())
                    .name(hospital.getName())
                    .build();
        }
    }
}
