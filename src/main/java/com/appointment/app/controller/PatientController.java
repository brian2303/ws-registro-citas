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

import com.appointment.app.dto.PatientDTO;
import com.appointment.app.entity.Patient;
import com.appointment.app.service.PatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	/**
	 * Utilizado para crear un nuevo paciente en el sistema.
	 * 
	 * @param patientDTO información para generar un nuevo entity que posteriormente sera persistido
	 * @return un PatientDTO que se transforma apartir de la entidad persistida.
	 */
	@PostMapping
	public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientDTO));
	}
	/**
	 * Utilizado para leer toda la lista de pacientes previamente registrados en el sistema
	 * 
	 * @return una lista de todos los pacientes previamente transformadas a PatientDTO
	 * con la finalidad de devolver unicamente la información necesaria por paciente
	 */
	@GetMapping
	public ResponseEntity<List<PatientDTO>> readAll(){
		return ResponseEntity.ok().body(patientService.findAll()) ;
	}
	
	/**
	 * Utilizado para obtener la información de un paciente
	 * 
	 * @param id utilizado para buscar una paciente concreto
	 * @return un PatientDTO que se genera en el servicio luego de buscar el entity por id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(patientService.findById(id));
	}
	/**
	 * Utilizado para actualizar la información referente a un paciente en concreto
	 * 
	 * @param patientDetails detalles que se utilizan para actualizar la entidad 
	 * @param id utilizado para buscar la entidad a actualizar
	 * @return un PatientDTO el cual tendra la información actualizada
	 */
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
	/**
	 * Utilizado para eliminar un paciente 
	 * que se identificara de manera univoca por su id.
	 * 
	 * @param id utilizado para eliminar una paciente concreto
	 */
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id){
		patientService.deleteById(id);
	}
}
