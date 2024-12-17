package com.example.spring_study_hospital.domain.doctor.application;

import com.example.spring_study_hospital.apiPayload.code.status.ErrorStatus;
import com.example.spring_study_hospital.apiPayload.exception.handler.GeneralException;
import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.department.domain.DepartmentRepository;
import com.example.spring_study_hospital.domain.doctor.domain.Doctor;
import com.example.spring_study_hospital.domain.doctor.domain.repository.DoctorRepository;
import com.example.spring_study_hospital.domain.doctor.dto.DoctorRequestDTO;
import com.example.spring_study_hospital.domain.doctor.dto.DoctorResponseDTO;
import com.example.spring_study_hospital.domain.hospital.domain.Hospital;
import com.example.spring_study_hospital.domain.hospital.domain.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    // 특정 병원의 모든 의사 조회
    public List<DoctorResponseDTO.DoctorViewResponseDTO> getDoctorsByHospital(Long hospitalId) {
        // 병원 존재 여부 확인
        if (!hospitalRepository.existsById(hospitalId)) {
            throw new GeneralException(ErrorStatus.HOSPITAL_NOT_FOUND);
        }

        return doctorRepository.findAllByHospitalId(hospitalId)
                .stream()
                .map(DoctorResponseDTO.DoctorViewResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    // 의사 등록
    public DoctorResponseDTO.DoctorCreateResponseDTO createDoctor(DoctorRequestDTO requestDTO) {

        // 병원 이름으로 병원 찾기
        Hospital hospital = hospitalRepository.findByName(requestDTO.getHospitalName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.HOSPITAL_NOT_FOUND));

        // 해당 병원의 진료과 찾기
        Department department = departmentRepository.findByHospitalIdAndName(
                hospital.getId(),
                requestDTO.getDepartmentName()
        ).orElseThrow(() -> new GeneralException(ErrorStatus.DEPARTMENT_NOT_FOUND));

        Doctor doctor = Doctor.builder()
                .department(department)
                .career(requestDTO.getCareer())
                .name(requestDTO.getName())
                .build();

        doctor = doctorRepository.save(doctor);
        return DoctorResponseDTO.DoctorCreateResponseDTO.from(doctor);
    }
}
