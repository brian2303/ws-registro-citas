package com.appointment.app.converter;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.PatientDTO;
import com.appointment.app.entity.Patient;

@Service
public class PatientConverter implements Converter<Patient, PatientDTO> {

	@Override
	public Patient fromDTO(PatientDTO dto) {
		Patient patient = new Patient();
		patient.setIdPatient(dto.getIdPatient());
		patient.setName(dto.getName());
		patient.setLastname(dto.getLastname());
		patient.setDateOfBirth(dto.getDateOfBirth());
		patient.setIdentification(dto.getIdentification());
		patient.setIdentificationType(dto.getIdentificationType());
		patient.setAffiliation(dto.getAffiliation());
		patient.setMedicalHistory(dto.getMedicalHistory());
		return patient;
	}

	@Override
	public PatientDTO fromEntity(Patient entity) {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setIdPatient(entity.getIdPatient());
		patientDTO.setName(entity.getName());
		patientDTO.setLastname(entity.getLastname());
		patientDTO.setDateOfBirth(entity.getDateOfBirth());
		patientDTO.setIdentification(entity.getIdentification());
		patientDTO.setIdentificationType(entity.getIdentificationType());
		patientDTO.setAffiliation(entity.getAffiliation());
		patientDTO.setMedicalHistory(entity.getMedicalHistory());
		return patientDTO;
	}

	
	

}
