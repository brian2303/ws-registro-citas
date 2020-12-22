package com.appointment.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/appointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping
	public List<AppointmentDTO> readAll(){
		return appointmentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentDTO> read(@PathVariable Integer id){
		AppointmentDTO appointmentDTO = appointmentService.findById(id);
		return ResponseEntity.ok().body(appointmentDTO);
	}
	
	@PostMapping
	public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointment){
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointment));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id){
		appointmentService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDetails,@PathVariable Integer id){
		AppointmentDTO appointmentDTO = appointmentService.findById(id);
		appointmentDTO.setAppointmentHour(appointmentDetails.getAppointmentHour());
		appointmentDTO.setAppointmentDate(appointmentDetails.getAppointmentDate());
		appointmentDTO.setIdPatient(appointmentDetails.getIdPatient());
		appointmentDTO.setIdDoctor(appointmentDetails.getIdDoctor());
		return ResponseEntity.ok().body(appointmentService.save(appointmentDTO));
	}
	
	@PostMapping("/available")
	public List<String> getAvailableAppointmentsByDoctor(@RequestBody AppointmentAvailableDTO appointment){
		return appointmentService.getAvailable(appointment.getId(), appointment.getDate());
	}
	
	@PostMapping("/quantity")
	public Integer getQuantityAppointmentByPatient(@RequestBody AppointmentAvailableDTO appointment) {
		return appointmentService.getQuantityAppointmentByPatient(appointment.getId(),appointment.getDate());
	}
	
}
