package com.appointment.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.app.entity.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{
	public List<Appointment> findByDoctorIdDoctorAndAppointmentDate(Integer idDoctor, LocalDate date);
	public List<Appointment> findByPatientIdPatientAndAppointmentDate(Integer idPatient, LocalDate date);
}
