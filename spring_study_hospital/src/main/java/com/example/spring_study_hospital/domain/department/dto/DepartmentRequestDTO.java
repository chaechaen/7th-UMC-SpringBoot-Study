package com.example.spring_study_hospital.domain.department.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DepartmentRequestDTO {

    private String hospitalName;
    private String departmentName;
    public String telNum;
}
