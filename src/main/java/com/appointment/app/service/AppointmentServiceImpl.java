package com.appointment.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Appointment> findById(Integer id) {
		return appointmentRepository.findById(id);
	}

	@Override
	@Transactional
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
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
		List<String> scheduleDoctor = new ArrayList<String>();
			List<String> scheduled = appointmentsScheduled.stream().map(a -> a.getAppointmentHour()).collect(Collectors.toList());
			Optional<Doctor> doctorSelected = doctorRepository.findById(doctor);
			if(doctorSelected.isPresent()) {
				Integer startSchedule = Integer.parseInt(doctorSelected.get().getStartSchedule());
				Integer endSchedule = Integer.parseInt(doctorSelected.get().getEndSchedule());
				for(int hour = startSchedule;hour<endSchedule;hour++) {
					scheduleDoctor.add(String.valueOf(hour));
				}
				return scheduleDoctor.stream().filter(s -> !scheduled.contains(s)).collect(Collectors.toList());
			}
			return new ArrayList<>();
	}
	
	@Override
	@Transactional
	public Integer getQuantityAppointmentByPatient(Integer patient,LocalDate date) {
		List<Appointment> appointments = appointmentRepository.findByPatientIdPatientAndAppointmentDate(patient, date);
		return appointments.size();
	}

}
