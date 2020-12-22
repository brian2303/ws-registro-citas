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
	
	/**
	 * 
	 * @param patientDTO información para generar un nuevo entity que posteriormente sera persistido
	 * @return un PatientDTO que se transforma apartir de la entidad persistida.
	 */
	@PostMapping
	public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientDTO));
	}
	/**
	 * 
	 * @return una lista de todos los pacientes previamente transformadas a PatientDTO
	 * con la finalidad de devolver unicamente la información necesaria por paciente
	 */
	@GetMapping
	public ResponseEntity<List<PatientDTO>> readAll(){
		return ResponseEntity.ok().body(patientService.findAll()) ;
	}
	
	/**
	 * 
	 * @param id utilizado para buscar una entitdad concreta
	 * @return un PatientDTO que se genera en el servicio luego de buscarl el entity por id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(patientService.findById(id));
	}
	/**
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
	 * 
	 * @param id utilizado para eliminar una entidad concreta
	 */
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id){
		patientService.deleteById(id);
	}
}
