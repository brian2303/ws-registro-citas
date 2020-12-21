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

import com.appointment.app.entity.Patient;
import com.appointment.app.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	public ResponseEntity<Patient> create(@RequestBody Patient patient){
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patient));
	}
	
	@GetMapping
	public List<Patient> readAll(){
		return patientService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> read(@PathVariable Integer id) {
		Optional<Patient> oPatient = patientService.findById(id);
		if(!oPatient.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oPatient.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> update(@RequestBody Patient patientDetails,@PathVariable Integer id){
		Optional<Patient> patient = patientService.findById(id);
		if(!patient.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		patient.get().setName(patientDetails.getName());
		patient.get().setLastname(patientDetails.getLastname());
		patient.get().setAffiliation(patientDetails.getAffiliation());
		patient.get().setMedicalHistory(patientDetails.getMedicalHistory());
		patient.get().setIdentificationType(patientDetails.getIdentificationType());
		patient.get().setIdentification(patientDetails.getIdentification());
		patient.get().setDateOfBirth(patientDetails.getDateOfBirth());
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patient.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete (@PathVariable Integer id){
		if(!patientService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		patientService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
