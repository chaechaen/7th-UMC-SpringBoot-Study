package com.example.spring_study_hospital.domain.patient.application;

import com.example.spring_study_hospital.apiPayload.code.status.ErrorStatus;
import com.example.spring_study_hospital.apiPayload.exception.handler.GeneralException;
import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.department.domain.DepartmentRepository;
import com.example.spring_study_hospital.domain.patient.domain.Gender;
import com.example.spring_study_hospital.domain.patient.domain.Patient;
import com.example.spring_study_hospital.domain.patient.domain.repository.PatientRepository;
import com.example.spring_study_hospital.domain.patient.dto.PatientResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public List<PatientResponseDTO.PatientViewResponseDTO> getPatientsByHospitalAndDepartment(
            String hospitalName, String departmentName) {
        // 먼저 Department 찾기
        Department department = departmentRepository.findByHospitalNameAndDepartmentName(
                        hospitalName, departmentName)
                .orElseThrow(() -> new GeneralException(ErrorStatus.DEPARTMENT_NOT_FOUND));

        List<Patient> patients = patientRepository.findAllByHospitalNameAndDepartmentName(
                hospitalName, departmentName);

        return patients.stream()
                .map(patient -> PatientResponseDTO.PatientViewResponseDTO.from(patient, department))
                .collect(Collectors.toList());
    }

    // 환자 생성 (ReservationService에서 호출)
    public Patient createPatient(String name, Integer age, Gender gender) {
        Patient patient = Patient.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .build();
        return patientRepository.save(patient);
    }
}