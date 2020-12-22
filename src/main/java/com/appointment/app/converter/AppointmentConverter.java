package com.appointment.app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.app.dto.AppointmentDTO;
import com.appointment.app.entity.Appointment;
import com.appointment.app.service.DoctorService;
import com.appointment.app.service.PatientService;

@Service
public class AppointmentConverter implements Converter<Appointment, AppointmentDTO> {

	@Autowired
	DoctorService doctorService;
	@Autowired
	PatientService patientService;
	@Autowired
	DoctorConverter doctorConverter;
	@Autowired
	PatientConverter patientConverter;
	
	
	@Override
	public Appointment fromDTO(AppointmentDTO dto) {
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(dto.getIdAppointment());
		appointment.setAppointmentDate(dto.getAppointmentDate());
		appointment.setAppointmentHour(dto.getAppointmentHour());
		appointment.setDoctor(doctorConverter.fromDTO(doctorService.findById(dto.getIdDoctor())));
		appointment.setPatient(patientConverter.fromDTO(patientService.findById(dto.getIdPatient())));
		return appointment;
	}

	@Override
	public AppointmentDTO fromEntity(Appointment entity) {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setIdAppointment(entity.getIdAppointment());
		appointmentDTO.setAppointmentDate(entity.getAppointmentDate());
		appointmentDTO.setAppointmentHour(entity.getAppointmentHour());
		appointmentDTO.setIdDoctor(entity.getDoctor().getIdDoctor());
		appointmentDTO.setNameDoctor(entity.getDoctor().getName());
		appointmentDTO.setIdPatient(entity.getPatient().getIdPatient());
		appointmentDTO.setNamePatient(entity.getPatient().getName());
		return appointmentDTO;
	}

}
