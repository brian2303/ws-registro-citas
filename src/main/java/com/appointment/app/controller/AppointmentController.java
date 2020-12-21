package com.appointment.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
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

import com.appointment.app.entity.Appointment;
import com.appointment.app.service.AppointmentService;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping
	public List<Appointment> readAll(){
		return appointmentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Appointment> read(@PathVariable Integer id){
		Optional<Appointment> oAppointment = appointmentService.findById(id);
		if(!oAppointment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(oAppointment.get());
	}
	
	@PostMapping
	public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointment));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Appointment> delete (@PathVariable Integer id){
		if(!appointmentService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		appointmentService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Appointment> update(@RequestBody Appointment appointmentDetails,@PathVariable Integer id){
		Optional<Appointment> oAppointment = appointmentService.findById(id);
		if(!oAppointment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		oAppointment.get().setAppointmentHour(appointmentDetails.getAppointmentHour());
		oAppointment.get().setAppointmentDate(appointmentDetails.getAppointmentDate());
		oAppointment.get().setPatient(appointmentDetails.getPatient());
		oAppointment.get().setDoctor(appointmentDetails.getDoctor());
		return ResponseEntity.ok().body(appointmentService.save(oAppointment.get()));
	}
	
	@PostMapping("/available")
	public List<String> getAvailableAppointmentsByDoctor(@RequestBody String appointmentReq)
	{
		JSONObject req = new JSONObject(appointmentReq);
		LocalDate date = LocalDate.parse((String) req.get("date"));
		Integer idDoctor = (Integer) req.get("idDoctor");
		return appointmentService.getAvailable(idDoctor, date);
	}
	
	
	@PostMapping("/quantity")
	public Integer getQuantityAppointmentByPatient(@RequestBody String patientAndDate) {
		JSONObject json = new JSONObject(patientAndDate);
		LocalDate date = LocalDate.parse((String) json.get("date"));
		Integer idPatient = (Integer) json.get("idPatient");
		return appointmentService.getQuantityAppointmentByPatient(idPatient, date);
	}
	
}
