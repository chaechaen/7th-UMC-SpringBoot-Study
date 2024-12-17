package com.example.spring_study_hospital.domain.doctor.presentation;

import com.example.spring_study_hospital.apiPayload.ApiResponse;
import com.example.spring_study_hospital.domain.doctor.application.DoctorService;
import com.example.spring_study_hospital.domain.doctor.dto.DoctorRequestDTO;
import com.example.spring_study_hospital.domain.doctor.dto.DoctorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "의사")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    // 특정 병원의 모든 의사 조회
    @Operation(summary = "특정 병원의 모든 의사 조회")
    @GetMapping("/hospital/{hospitalId}")
    public ApiResponse<List<DoctorResponseDTO.DoctorViewResponseDTO>> getAllDoctorsByHospital(@PathVariable Long hospitalId) {
        return ApiResponse.onSuccess(doctorService.getDoctorsByHospital(hospitalId));
    }

    // 의사 등록
    @Operation(summary = "특정 병원의 특정 진료과에 의사 등록")
    @PostMapping
    public ApiResponse<DoctorResponseDTO.DoctorCreateResponseDTO> createDoctor(@RequestBody DoctorRequestDTO requestDTO) {
        DoctorResponseDTO.DoctorCreateResponseDTO responseDTO = doctorService.createDoctor(requestDTO);
        return ApiResponse.onSuccess(responseDTO);
    }

}
