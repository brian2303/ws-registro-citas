package com.appointment.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.PatientDTO;

@Service
public interface PatientService {
	/**
	 * busca todos los pacientes que esten registrados en la base de datos 
	 * para devolvernos los datos en un DTO previamente configurado
	 * 
	 * @return el total de pacientes transformadas en un DTO
	 */
	public List<PatientDTO> findAll();
	/**
	 * 
	 * encuentra un paciente de forma univoca por un id
	 * para luego devolvernos un DTO unicamente con los datos 
	 * necesarios para un paciente
	 * 
	 * @param id id usado para buscar un objeto concreto
	 * @return un DTO configurado en el paquete app.appointment.app.dto
	 */
	public PatientDTO findById(Integer id);
	
	/**
	 * 
	 * Crea un nuevo registro en la base de datos de un paciente
	 * 
	 * @param patientDTO recibe un objeto DTO que posteriormente se tranforma a un entity
	 * 			para poder realizar la persistencia
	 * @return  un DTO que se transforma por medio del entity que se persiste
	 */
	public PatientDTO save(PatientDTO patientDTO);
	/**
	 * borra un paciente de la base de datos identificandolo de manera univoca por su id
	 * 
	 * @param id usado para eliminar una entidad concreta
	 */
	public void deleteById(Integer id);
}
