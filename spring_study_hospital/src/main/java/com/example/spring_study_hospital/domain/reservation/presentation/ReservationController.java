package com.example.spring_study_hospital.domain.reservation.presentation;

import com.example.spring_study_hospital.apiPayload.ApiResponse;
import com.example.spring_study_hospital.domain.patient.dto.PatientRequestDTO;
import com.example.spring_study_hospital.domain.reservation.application.ReservationService;
import com.example.spring_study_hospital.domain.reservation.dto.ReservationRequestDTO;
import com.example.spring_study_hospital.domain.reservation.dto.ReservationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예약")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "예약 생성 (신규/기존 환자)")
    @PostMapping
    public ApiResponse<ReservationResponseDTO.ReservationViewResponseDTO> createReservation(
            @RequestBody ReservationRequestDTO requestDTO) {
        return ApiResponse.onSuccess(reservationService.createReservation(requestDTO));
    }

    @Operation(summary = "특정 병원의 특정 진료과 예약 조회")
    @GetMapping("/hospital/{hospitalName}/department/{departmentName}")
    public ApiResponse<List<ReservationResponseDTO.ReservationViewResponseDTO>> getReservationsByHospitalAndDepartment(
            @PathVariable String hospitalName,
            @PathVariable String departmentName) {
        return ApiResponse.onSuccess(
                reservationService.getReservationsByHospitalAndDepartment(hospitalName, departmentName));
    }
}
