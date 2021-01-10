package com.appointment.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.AppointmentDTO;

@Service
public interface AppointmentService {
	
	/**
	 * Devuelve una lista con todas las citas agendadas en el sistema
	 * 
	 * @return el total de citas transformadas en un DTO con los datos necesarios para la vista
	 */
	public List<AppointmentDTO> findAll();
	
	/**
	 * busca una cita por un identificador unico y procesa el entity que retorna la base de datos
	 * para finalmente entregarnos un DTO con los valores necesarios.
	 * 
	 * @param id Usado para consultar una cita en concreto
	 * @return La cita por medio de un DTO configurado en el paquete com.appointment.app.dto
	 */
	public AppointmentDTO findById(Integer id);
	
	/**
	 * crea un nuevo registro en la base de datos de una cita
	 * 
	 * @param appointmentDTO  Sera transformado a entity para poder persitir la data
	 * @return Una vez guardado el entity se procede a realizar la transformación a DTO
	 * 		   para poder devolverlo en el controller
	 */
	public AppointmentDTO save(AppointmentDTO appointmentDTO);
	
	/**
	 * Elimina por id una cita concreta
	 * 
	 * @param id usado para eliminar una entidad concreta
	 */
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
	
	/**
	 * Obtiene la cantidad de citas agendadas por un paciente en una fecha determinada
	 * 
	 * @param patient corresponse al paciente para el que validaremos la cantidad de citas
	 * @param date es la fecha con la que validaremos la cantidad de citas por paciente
	 * @return El numero de citas agendadas por un paciente en una fecha determinada
	 */
	public Integer getQuantityAppointmentByPatient(Integer patient,LocalDate date);
}
