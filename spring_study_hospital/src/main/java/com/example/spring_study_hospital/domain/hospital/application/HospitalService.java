package com.example.spring_study_hospital.domain.hospital.application;

import com.example.spring_study_hospital.apiPayload.code.status.ErrorStatus;
import com.example.spring_study_hospital.apiPayload.exception.handler.GeneralException;
import com.example.spring_study_hospital.domain.hospital.domain.Hospital;
import com.example.spring_study_hospital.domain.hospital.domain.repository.HospitalRepository;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalRequestDTO;
import com.example.spring_study_hospital.domain.hospital.dto.HospitalResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    // 모든 병원 조회
    public List<HospitalResponseDTO.HospitalViewResponseDTO> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(HospitalResponseDTO.HospitalViewResponseDTO::from)
                .collect(Collectors.toList());
    }

    // 병원 등록
    @Transactional
    public HospitalResponseDTO.HospitalCreateResponseDTO createHospital(HospitalRequestDTO requestDTO) {

        // 이미 존재하는 병원인지 확인
        if (hospitalRepository.existsByName(requestDTO.getName())) {
            throw new GeneralException(ErrorStatus.HOSPITAL_ALREADY_EXISTS);
        }

        Hospital hospital = Hospital.builder()
                .name(requestDTO.getName())
                .address(requestDTO.getAddress())
                .departments(new ArrayList<>())
                .build();

        hospital = hospitalRepository.save(hospital);
        return HospitalResponseDTO.HospitalCreateResponseDTO.from(hospital);
    }


}
