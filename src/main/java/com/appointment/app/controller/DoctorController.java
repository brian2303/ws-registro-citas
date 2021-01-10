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

import com.appointment.app.dto.DoctorDTO;
import com.appointment.app.service.DoctorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * Utilizado para crear un doctor
	 * 
	 * @param doctorDTO datos que seran transformados a un entity para finalmente persistir la información
	 * @return un DoctorDTO que contiene unicamente la información necesaria para mostrar en la vista
	 */
	@PostMapping
	public ResponseEntity<DoctorDTO>  create(@RequestBody DoctorDTO doctorDTO){
		try {			
			return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(doctorDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	/**
	 * Utilizado para conocer la lista de doctores registrados previamente en el sistema
	 * 
	 * @return una lista de todos los doctores previamente transformadas a DoctorDTO
	 * con la finalidad de devolver unicamente la información necesaria por doctor
	 */
	@GetMapping
	public ResponseEntity<List<DoctorDTO>> readAll(){
		return ResponseEntity.ok().body(doctorService.findAll());
	}
	
	/**
	 * Utilizado para buscar un doctor en especifico por su id.
	 * 
	 * @param id utilizado para buscar un entity concreta
	 * @return un DoctorDTO transformado desde el servicio
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DoctorDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(doctorService.findById(id));
	}
	
	/**
	 * Utilizado para actualizar la información de un doctor
	 * 
	 * @param doctorDetails detalles que actualizaran el entity
	 * @param id utilizado para buscar el entity concreto a actualizar
	 * @return un DoctorDTO actualizado con los datos necesarios
	 */
	@PutMapping("/{id}")
	public ResponseEntity<DoctorDTO> update(@RequestBody DoctorDTO doctorDetails,@PathVariable Integer id){
			DoctorDTO doctor = doctorService.findById(id);
			doctor.setName(doctorDetails.getName());
			doctor.setIdentificationType(doctorDetails.getIdentificationType());
			doctor.setIdentification(doctorDetails.getIdentification());
			doctor.setProfessionalCard(doctorDetails.getProfessionalCard());
			doctor.setExperience(doctorDetails.getExperience());
			doctor.setSpeciality(doctorDetails.getSpeciality());
			doctor.setStartSchedule(doctorDetails.getStartSchedule());
			doctor.setEndSchedule(doctorDetails.getEndSchedule());
			return ResponseEntity.ok().body(doctorService.save(doctor));
		
	}
	
	/**
	 * Utilizado para eliminar un doctor de manera univoca
	 * identificado por su id
	 * 
	 * @param id utilizado para eliminar un entity concreto
	 */
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id){
		try {
			doctorService.deleteById(id);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
	}
	
}
