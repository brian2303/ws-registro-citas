package com.appointment.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appointment.app.entity.Patient;
import com.appointment.app.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Patient> findById(Integer id) {
		return patientRepository.findById(id);
	}

	@Override
	@Transactional
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		patientRepository.deleteById(id);
	}
	
}
