package com.appointment.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.appointment.app.entity.Doctor;
import com.appointment.app.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<Doctor> create(@RequestBody Doctor doctor){
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(doctor));
	}
	
	@GetMapping
	public List<Doctor> readAll(){
		return doctorService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> read(@PathVariable Integer id) {
		Optional<Doctor> oDoctor = doctorService.findById(id);
		if(!oDoctor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oDoctor.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> update(@RequestBody Doctor doctorDetails,@PathVariable Integer id){
		Optional<Doctor> doctor = doctorService.findById(id);
		if(!doctor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		doctor.get().setName(doctorDetails.getName());
		doctor.get().setIdentificationType(doctorDetails.getIdentificationType());
		doctor.get().setIdentification(doctorDetails.getIdentification());
		doctor.get().setProfessionalCard(doctorDetails.getProfessionalCard());
		doctor.get().setExperience(doctorDetails.getExperience());
		doctor.get().setSpeciality(doctorDetails.getSpeciality());
		doctor.get().setStartSchedule(doctorDetails.getStartSchedule());
		doctor.get().setEndSchedule(doctorDetails.getEndSchedule());
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(doctor.get()));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete (@PathVariable Integer id){
		if(!doctorService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		doctorService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
