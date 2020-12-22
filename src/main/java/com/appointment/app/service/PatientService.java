package com.appointment.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.PatientDTO;

@Service
public interface PatientService {
	
	public List<PatientDTO> findAll();
	
	public PatientDTO findById(Integer id);
	
	public PatientDTO save(PatientDTO patientDTO);
	
	public void deleteById(Integer id);
}
