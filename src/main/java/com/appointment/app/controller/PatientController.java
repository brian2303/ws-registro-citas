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

import com.appointment.app.dto.PatientDTO;
import com.appointment.app.entity.Patient;
import com.appointment.app.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientDTO));
	}
	
	@GetMapping
	public ResponseEntity<List<PatientDTO>> readAll(){
		return ResponseEntity.ok().body(patientService.findAll()) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(patientService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PatientDTO> update(@RequestBody Patient patientDetails,@PathVariable Integer id){
			PatientDTO patient = patientService.findById(id);
			patient.setName(patientDetails.getName());
			patient.setLastname(patientDetails.getLastname());
			patient.setAffiliation(patientDetails.getAffiliation());
			patient.setMedicalHistory(patientDetails.getMedicalHistory());
			patient.setIdentificationType(patientDetails.getIdentificationType());
			patient.setIdentification(patientDetails.getIdentification());
			patient.setDateOfBirth(patientDetails.getDateOfBirth());
			return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patient));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id){
		patientService.deleteById(id);
	}
}
