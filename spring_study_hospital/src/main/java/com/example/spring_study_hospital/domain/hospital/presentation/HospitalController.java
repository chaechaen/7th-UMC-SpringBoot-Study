package com.example.spring_study_hospital.domain.hospital.presentation;

import com.example.spring_study_hospital.apiPayload.ApiResponse;
import com.example.spring_study_hospital.domain.hospital.application.HospitalService;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalRequestDTO;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "병원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    // 모든 병원 조회
    @Operation(summary = "모든 병원 조회")
    @GetMapping
    public ApiResponse<List<HospitalResponseDTO.HospitalViewResponseDTO>> getAllHospitals() {
        return ApiResponse.onSuccess(hospitalService.getAllHospitals());
    }

    // 병원 등록
    @Operation(summary = "병원 등록")
    @PostMapping
    public ApiResponse<HospitalResponseDTO.HospitalCreateResponseDTO> createHospital(
            @RequestBody HospitalRequestDTO requestDTO) {
        return ApiResponse.onSuccess(hospitalService.createHospital(requestDTO));
    }
}
