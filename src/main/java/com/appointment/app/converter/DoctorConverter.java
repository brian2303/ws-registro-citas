package com.appointment.app.converter;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.DoctorDTO;
import com.appointment.app.entity.Doctor;

@Service
public class DoctorConverter implements Converter<Doctor, DoctorDTO> {

	@Override
	public Doctor fromDTO(DoctorDTO dto) {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor(dto.getIdDoctor());
		doctor.setName(dto.getName());
		doctor.setIdentificationType(dto.getIdentificationType());
		doctor.setIdentification(dto.getIdentification());
		doctor.setProfessionalCard(dto.getProfessionalCard());
		doctor.setExperience(dto.getExperience());
		doctor.setSpeciality(dto.getSpeciality());
		doctor.setStartSchedule(dto.getStartSchedule());
		doctor.setEndSchedule(dto.getEndSchedule());
		return doctor;
	}

	@Override
	public DoctorDTO fromEntity(Doctor doctorEntity) {
		DoctorDTO  doctorDTO = new DoctorDTO();
		doctorDTO.setIdDoctor(doctorEntity.getIdDoctor());
		doctorDTO.setName(doctorEntity.getName());
		doctorDTO.setIdentificationType(doctorEntity.getIdentificationType());
		doctorDTO.setIdentification(doctorEntity.getIdentification());
		doctorDTO.setProfessionalCard(doctorEntity.getProfessionalCard());
		doctorDTO.setExperience(doctorEntity.getExperience());
		doctorDTO.setSpeciality(doctorEntity.getSpeciality());
		doctorDTO.setStartSchedule(doctorEntity.getStartSchedule());
		doctorDTO.setEndSchedule(doctorEntity.getEndSchedule());
		return doctorDTO;
	}

}
