package com.example.spring_study_hospital.domain.reservation.application;

import com.example.spring_study_hospital.apiPayload.code.status.ErrorStatus;
import com.example.spring_study_hospital.apiPayload.exception.handler.GeneralException;
import com.example.spring_study_hospital.domain.department.domain.Department;
import com.example.spring_study_hospital.domain.department.domain.DepartmentRepository;
import com.example.spring_study_hospital.domain.doctor.domain.Doctor;
import com.example.spring_study_hospital.domain.doctor.domain.repository.DoctorRepository;
import com.example.spring_study_hospital.domain.hospital.domain.Hospital;
import com.example.spring_study_hospital.domain.hospital.domain.repository.HospitalRepository;
import com.example.spring_study_hospital.domain.patient.application.PatientService;
import com.example.spring_study_hospital.domain.patient.domain.Patient;
import com.example.spring_study_hospital.domain.patient.domain.repository.PatientRepository;
import com.example.spring_study_hospital.domain.reservation.domain.Reservation;
import com.example.spring_study_hospital.domain.reservation.domain.ReservationStatus;
import com.example.spring_study_hospital.domain.reservation.domain.repository.ReservationRepository;
import com.example.spring_study_hospital.domain.reservation.dto.ReservationRequestDTO;
import com.example.spring_study_hospital.domain.reservation.dto.ReservationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    @Transactional
    public ReservationResponseDTO.ReservationViewResponseDTO createReservation(ReservationRequestDTO requestDTO) {
        Hospital hospital = hospitalRepository.findByName(requestDTO.getHospitalName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.HOSPITAL_NOT_FOUND));

        Department department = departmentRepository.findByHospitalNameAndDepartmentName(
                        requestDTO.getHospitalName(), requestDTO.getDepartmentName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.DEPARTMENT_NOT_FOUND));

        Doctor doctor = doctorRepository.findByNameAndDepartment(
                        requestDTO.getDoctorName(), department)
                .orElseThrow(() -> new GeneralException(ErrorStatus.DOCTOR_NOT_FOUND));

        // 환자 조회 또는 생성
        Patient patient = patientRepository.findByName(requestDTO.getPatientName())
                .orElseGet(() -> patientService.createPatient(
                        requestDTO.getPatientName(),
                        requestDTO.getPatientAge(),
                        requestDTO.getPatientGender()));

        // 중복 예약 체크
        if (reservationRepository.existsByPatientAndDoctorAndStatusNot(
                patient, doctor, ReservationStatus.COMPLETED)) {
            throw new GeneralException(ErrorStatus.RESERVATION_ALREADY_EXISTS);
        }

        Reservation reservation = Reservation.builder()
                .patient(patient)
                .doctor(doctor)
                .reservationDate(requestDTO.getReservationDate())
                .status(ReservationStatus.AWAIT)
                .price(requestDTO.getPrice())
                .build();

        reservation = reservationRepository.save(reservation);
        return ReservationResponseDTO.ReservationViewResponseDTO.from(reservation);
    }

    public List<ReservationResponseDTO.ReservationViewResponseDTO> getReservationsByHospitalAndDepartment(
            String hospitalName, String departmentName) {
        // 병원과 진료과 존재 여부 확인
        Hospital hospital = hospitalRepository.findByName(hospitalName)
                .orElseThrow(() -> new GeneralException(ErrorStatus.HOSPITAL_NOT_FOUND));

        Department department = departmentRepository.findByHospitalNameAndDepartmentName(
                        hospitalName, departmentName)
                .orElseThrow(() -> new GeneralException(ErrorStatus.DEPARTMENT_NOT_FOUND));

        // 예약 목록 조회
        List<Reservation> reservations = reservationRepository.findAllByHospitalNameAndDepartmentName(
                hospitalName, departmentName);

        // DTO 변환
        return reservations.stream()
                .map(ReservationResponseDTO.ReservationViewResponseDTO::from)
                .collect(Collectors.toList());
    }
}
