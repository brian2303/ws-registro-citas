package com.appointment.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.DoctorDTO;
@Service
public interface DoctorService {
	
	/**
	 * 
	 * @return el total de doctores transformadas en un DTO
	 */
	public List<DoctorDTO> findAll();
	/**
	 * 
	 * @param id usado para buscar un objeto concreto
	 * @return un DTO configurado en el paquete app.appointment.app.dto
	 */
	public DoctorDTO findById(Integer id);
	
	/**
	 * 
	 * @param doctorDTO recibe un objeto DTO que posteriormente se tranforma a un entity
	 * 			para poder realizar la persistencia
	 * @return un DTO que se transforma por medio del entity que se persiste
	 */
	public DoctorDTO save(DoctorDTO doctorDTO);
	
	/**
	 * 
	 * @param id usado para eliminar una entidad concreta
	 */
	public void deleteById(Integer id);
}
