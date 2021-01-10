package com.appointment.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.DoctorDTO;
@Service
public interface DoctorService {
	
	/**
	 * Devuelve una lista con todas los doctores registrados en el sistema
	 * 
	 * @return el total de doctores transformadas en un DTO
	 */
	public List<DoctorDTO> findAll();
	/**
	 * 
	 * Se encarga de devolver un doctor con los datos definidos en el DTO
	 * 
	 * @param id usado para buscar un objeto concreto
	 * @return un DTO configurado en el paquete app.appointment.app.dto
	 */
	public DoctorDTO findById(Integer id);
	
	/**
	 * Utilizado para guardar un nuevo registro de un doctor 
	 * 
	 * @param doctorDTO recibe un objeto DTO que posteriormente se tranforma a un entity
	 * 			para poder realizar la persistencia
	 * @return un DTO que se transforma por medio del entity que se persiste
	 */
	public DoctorDTO save(DoctorDTO doctorDTO);
	
	/**
	 * elimina un doctor de la base de datos identificandolo de manera univoca por su id
	 * 
	 * @param id usado para eliminar una entidad concreta
	 */
	public void deleteById(Integer id);
}
