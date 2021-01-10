package com.appointment.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.app.dto.AppointmentAvailableDTO;
import com.appointment.app.dto.AppointmentDTO;
import com.appointment.app.service.AppointmentService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/appointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	/**
	 * Utilizado para leer todas las citas que estan agendadas en el sistema
	 * devolviendo unicamente la información necesaria para la cita (fecha,hora,paciente y doctor)
	 * 
	 * @return una lista de todos las citas transformadas en un objeto DTO
	 */
	@GetMapping
	public List<AppointmentDTO> readAll(){
		return appointmentService.findAll();
	}
	
	/**
	 * Utilizado para leer una cita en concreto usando como 
	 * identificador su id que sera unico por cita
	 * 
	 * @param id utilizado para buscar un entity concreta
	 * @return una cita transformada en un AppointmentDTO desde el servicio
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentDTO> read(@PathVariable Integer id){
		AppointmentDTO appointmentDTO = appointmentService.findById(id);
		return ResponseEntity.ok().body(appointmentDTO);
	}
	
	/**
	 * Utilizado para crear una nueva cita en el sistema 
	 * 
	 * @param appointment utilizado para crear un nuevo registro en la Base de datos
	 * 			la transformación del DTO se realiza en el servicio
	 * @return un objeto de tipo Appointment DTO que se genera luego de persistir el entity
	 * 			en la capa de servicio
	 */
	@PostMapping
	public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointment){
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointment));
	}
	
	/**
	 * Utilizado para borrar una cita
	 * 
	 * @param id utilizado para eliminar una entidad concreta
	 */
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id){
		appointmentService.deleteById(id);
	}
	
	/**
	 * Utilizado para actualizar una cita.
	 * 
	 * @param appointmentDetails detalles que seran utilizados para actualizar la información
	 * 			de una entidad.
	 * @param id identificador unico para la actualización de la entidad
	 * @return Un Appointment DTO que se genera luego de persistir la entidad
	 */
	@PutMapping("/{id}")
	public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDetails,@PathVariable Integer id){
		AppointmentDTO appointmentDTO = appointmentService.findById(id);
		appointmentDTO.setAppointmentHour(appointmentDetails.getAppointmentHour());
		appointmentDTO.setAppointmentDate(appointmentDetails.getAppointmentDate());
		appointmentDTO.setIdPatient(appointmentDetails.getIdPatient());
		appointmentDTO.setIdDoctor(appointmentDetails.getIdDoctor());
		return ResponseEntity.ok().body(appointmentService.save(appointmentDTO));
	}
	
	/**
	 * Utilizado para conocer el horario disponible de un doctor en una fecha determinada
	 * 
	 * @param appointment recibe el id del doctor y la fecha de la cita posterior seran
	 * 			transformadas a un AppointmentAvailableDTO: esta clase contiende los atributos id y fechaCita
	 * @return una lista de tipo String con los horarios de atención disponibles para un doctor 
	 * 			en una fecha determinada
	 */
	@PostMapping("/available")
	public List<String> getAvailableAppointmentsByDoctor(@RequestBody AppointmentAvailableDTO appointment){
		return appointmentService.getAvailable(appointment.getId(), appointment.getDate());
	}
	
	/**
	 * Utilizado para conocer la cantidad de citas que tiene asignadas un paciente
	 * en una fecha determinada
	 * 
	 * @param appointment parametro que recibe el id del paciente y la fecha de la cita
	 * 			transformadas a un AppointmentAvailableDTO: esta clase contiende los atributos id y fechaCita
	 * @return el numero de citas asignadas por paciente en una fecha dada
	 */
	@PostMapping("/quantity")
	public Integer getQuantityAppointmentByPatient(@RequestBody AppointmentAvailableDTO appointment) {
		return appointmentService.getQuantityAppointmentByPatient(appointment.getId(),appointment.getDate());
	}
	
}
