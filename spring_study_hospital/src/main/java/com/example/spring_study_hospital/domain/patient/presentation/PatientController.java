package com.example.spring_study_hospital.domain.patient.presentation;

import com.example.spring_study_hospital.apiPayload.ApiResponse;
import com.example.spring_study_hospital.domain.patient.application.PatientService;
import com.example.spring_study_hospital.domain.patient.dto.PatientRequestDTO;
import com.example.spring_study_hospital.domain.patient.dto.PatientResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "환자")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    // 특정 병원의 특정 진료과의 모든 환자 조회
    @Operation(summary = "특정 병원의 특정 진료과의 모든 환자 조회")
    @GetMapping("/hospital/{hospitalName}/department/{departmentName}")
    public ApiResponse<List<PatientResponseDTO.PatientViewResponseDTO>> getPatientsByHospitalAndDepartment(
            @PathVariable String hospitalName,
            @PathVariable String departmentName) {
        return ApiResponse.onSuccess(
                patientService.getPatientsByHospitalAndDepartment(hospitalName, departmentName));
    }
}
