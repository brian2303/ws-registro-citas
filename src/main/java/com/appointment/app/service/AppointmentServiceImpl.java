package com.appointment.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appointment.app.converter.AppointmentConverter;
import com.appointment.app.dto.AppointmentDTO;
import com.appointment.app.entity.Appointment;
import com.appointment.app.entity.Doctor;
import com.appointment.app.repository.AppointmentRepository;
import com.appointment.app.repository.DoctorRepository;

/**
 * 
 * @author glzs
 *
 */
@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private AppointmentConverter appointmentConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<AppointmentDTO> findAll() {
		return appointmentRepository.findAll().stream()
				.map(a -> appointmentConverter.fromEntity(a))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public AppointmentDTO findById(Integer id) {
		return appointmentConverter.fromEntity(appointmentRepository.findById(id).get());
	}

	@Override
	@Transactional
	public AppointmentDTO save(AppointmentDTO appointmentDTO) {
		Appointment appointment = appointmentRepository.save(appointmentConverter.fromDTO(appointmentDTO));
		return  appointmentConverter.fromEntity(appointment);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		appointmentRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public List<String> getAvailable(Integer doctor, LocalDate date){
		List<Appointment> appointmentsScheduled = appointmentRepository.findByDoctorIdDoctorAndAppointmentDate(doctor, date);
		List<String> scheduleDoctor = new ArrayList<>();
			List<String> scheduled = appointmentsScheduled.stream().map(Appointment::getAppointmentHour).collect(Collectors.toList());
			Doctor doctorSelected = doctorRepository.findById(doctor).get();
			Integer startSchedule = Integer.parseInt(doctorSelected.getStartSchedule());
			Integer endSchedule = Integer.parseInt(doctorSelected.getEndSchedule());
			for(int hour = startSchedule;hour<endSchedule;hour++) {
				scheduleDoctor.add(String.valueOf(hour));
			}
			return scheduleDoctor.stream().filter(s -> !scheduled.contains(s)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public Integer getQuantityAppointmentByPatient(Integer patient,LocalDate date) {
		return appointmentRepository.findByPatientIdPatientAndAppointmentDate(patient, date).size();
	}

}
