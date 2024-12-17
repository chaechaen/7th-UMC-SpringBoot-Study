package com.example.spring_study_hospital.domain.department.dto;

import com.example.spring_study_hospital.domain.department.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DepartmentResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepartmentViewResponseDTO { // 진료과 응답
        private Long departmentId;

        private String name;

        private String telNum;

        public static DepartmentViewResponseDTO from(Department department) {
            return DepartmentViewResponseDTO.builder()
                    .departmentId(department.getId())
                    .name(department.getName())
                    .telNum(department.getTelNum())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepartmentForResponseDTO { // 의사, 병원 관련 진료과 정보

        private Long departmentId;
        private String name;

        public static DepartmentForResponseDTO from(Department department) {
            return DepartmentForResponseDTO.builder()
                    .departmentId(department.getId())
                    .name(department.getName())
                    .build();
        }
    }
}
