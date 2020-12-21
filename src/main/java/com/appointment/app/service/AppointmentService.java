package com.appointment.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.appointment.app.entity.Appointment;

@Service
public interface AppointmentService {
	
	public List<Appointment> findAll();
	
	public Optional<Appointment> findById(Integer id);
	
	public Appointment save(Appointment doctor);
	
	public void deleteById(Integer id);
	
	/**
	 * Obtiene la lista de citas disponibles para un doctor en una fecha determinada.
	 * 
	 * @param doctor Corresponde al doctor del cual se quiere obtener su horario de atención
	 * @param date Corresponde a la fecha de cual se quiere determinar los horarios.
	 * 
	 * @return Una lista de tipo String con los horarios del doctor en la fecha especificada. 
	 *         En caso de no encontrar el doctor, la lista retornará vacía.
	 */
	public List<String> getAvailable(Integer doctor, LocalDate date);
	
	public Integer getQuantityAppointmentByPatient(Integer patient,LocalDate date);
}
