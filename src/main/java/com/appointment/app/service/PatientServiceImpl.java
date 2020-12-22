package com.appointment.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appointment.app.converter.PatientConverter;
import com.appointment.app.dto.PatientDTO;
import com.appointment.app.entity.Patient;
import com.appointment.app.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	PatientConverter patientConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<PatientDTO> findAll() {
		return patientRepository.findAll()
				.stream()
				.map(p -> patientConverter.fromEntity(p))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PatientDTO findById(Integer id) {
		Patient patient = patientRepository.findById(id).get();
		return patientConverter.fromEntity(patient);
		
	}

	@Override
	@Transactional
	public PatientDTO save(PatientDTO patientDTO) {
		Patient patient = patientRepository.save(patientConverter.fromDTO(patientDTO));
		return patientConverter.fromEntity(patient);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		patientRepository.deleteById(id);
	}
	
}
