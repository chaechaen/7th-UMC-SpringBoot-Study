package com.example.spring_study_hospital.domain.department.presentation;

import com.example.spring_study_hospital.apiPayload.ApiResponse;
import com.example.spring_study_hospital.domain.department.application.DepartmentService;
import com.example.spring_study_hospital.domain.department.dto.DepartmentRequestDTO;
import com.example.spring_study_hospital.domain.department.dto.DepartmentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "진료과")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    // 특정 병원의 진료과 목록 조회
    @Operation(summary = "특정 병원의 진료과 목록 조회")
    @GetMapping("/hospital/{hospitalId}")
    public ApiResponse<List<DepartmentResponseDTO.DepartmentViewResponseDTO>> getDepartmentsByHospital(
            @PathVariable Long hospitalId) {
        return ApiResponse.onSuccess(departmentService.getDepartmentsByHospital(hospitalId));
    }

    // 진료과 등록
    @Operation(summary = "진료과 등록")
    @PostMapping
    public ApiResponse<DepartmentResponseDTO.DepartmentViewResponseDTO> createDepartment(
            @RequestBody DepartmentRequestDTO requestDTO) {
        return ApiResponse.onSuccess(departmentService.createDepartment(requestDTO));
    }
}