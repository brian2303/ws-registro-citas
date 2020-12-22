package com.appointment.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.app.entity.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{
	/**
	 * 
	 * @param idDoctor doctor concreto por el que se desea realizar la busqudeda
	 * @param date fecha en la cual se desea realizar la busqueda de citas asignadas por doctor
	 * @return lista total de citas filtradas por el doctor y la fecha
	 */
	public List<Appointment> findByDoctorIdDoctorAndAppointmentDate(Integer idDoctor, LocalDate date);
	/**
	 * 
	 * @param idPatient paciente concreto por el que se desea realizar la busqueda
	 * @param date fecha en la cual se desea realizar las consulta de citas agendadas por paciente
	 * @return lista de citas por paciente filtradas por fecha y paciente
	 */
	public List<Appointment> findByPatientIdPatientAndAppointmentDate(Integer idPatient, LocalDate date);
}
