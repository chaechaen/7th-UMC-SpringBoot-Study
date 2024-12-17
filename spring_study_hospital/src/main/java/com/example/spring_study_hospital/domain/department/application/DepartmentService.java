package com.example.spring_study_hospital.domain.department.application;

import com.example.spring_study_hospital.apiPayload.code.status.ErrorStatus;
import com.example.spring_study_hospital.apiPayload.exception.handler.GeneralException;
import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.department.domain.DepartmentRepository;
import com.example.spring_study_hospital.domain.department.dto.DepartmentRequestDTO;
import com.example.spring_study_hospital.domain.department.dto.DepartmentResponseDTO;
import com.example.spring_study_hospital.domain.hospital.domain.Hospital;
import com.example.spring_study_hospital.domain.hospital.domain.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    // 특정 병원의 진료과 목록 조회
    public List<DepartmentResponseDTO.DepartmentViewResponseDTO> getDepartmentsByHospital(Long hospitalId) {
        List<Department> departments = departmentRepository.findByHospitalId(hospitalId);
        return departments.stream()
                .map(DepartmentResponseDTO.DepartmentViewResponseDTO::from)
                .collect(Collectors.toList());
    }

    // 진료과 등록
    @Transactional
    public DepartmentResponseDTO.DepartmentViewResponseDTO createDepartment(DepartmentRequestDTO requestDTO) {

        // 병원 존재 여부 확인
        Hospital hospital = hospitalRepository.findByName(requestDTO.getHospitalName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.HOSPITAL_NOT_FOUND));

        // 같은 병원 내 동일 이름의 진료과 존재 여부 확인
        if (departmentRepository.existsByHospitalNameAndName(
                requestDTO.getHospitalName(),
                requestDTO.getDepartmentName())) {
            throw new GeneralException(ErrorStatus.DEPARTMENT_ALREADY_EXISTS);
        }

        Department department = Department.builder()
                .hospital(hospital)
                .name(requestDTO.getDepartmentName())
                .telNum(requestDTO.getTelNum())
                .doctors(new ArrayList<>())
                .build();

        department = departmentRepository.save(department);
        return DepartmentResponseDTO.DepartmentViewResponseDTO.from(department);
    }
}
