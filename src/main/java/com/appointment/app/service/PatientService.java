package com.appointment.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.appointment.app.entity.Patient;

@Service
public interface PatientService {
	
	public List<Patient> findAll();
	
	public Optional<Patient> findById(Integer id);
	
	public Patient save(Patient patient);
	
	public void deleteById(Integer id);
}
