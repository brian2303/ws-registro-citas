package com.appointment.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.PatientDTO;

@Service
public interface PatientService {
	/**
	 * 
	 * @return el total de pacientes transformadas en un DTO
	 */
	public List<PatientDTO> findAll();
	/**
	 * 
	 * @param id id usado para buscar un objeto concreto
	 * @return un DTO configurado en el paquete app.appointment.app.dto
	 */
	public PatientDTO findById(Integer id);
	
	/**
	 * 
	 * @param patientDTO recibe un objeto DTO que posteriormente se tranforma a un entity
	 * 			para poder realizar la persistencia
	 * @return  un DTO que se transforma por medio del entity que se persiste
	 */
	public PatientDTO save(PatientDTO patientDTO);
	/**
	 * 
	 * @param id usado para eliminar una entidad concreta
	 */
	public void deleteById(Integer id);
}
