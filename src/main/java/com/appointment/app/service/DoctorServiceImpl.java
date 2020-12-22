package com.appointment.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appointment.app.converter.DoctorConverter;
import com.appointment.app.dto.DoctorDTO;
import com.appointment.app.entity.Doctor;
import com.appointment.app.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	DoctorConverter doctorConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<DoctorDTO> findAll() {
		return doctorRepository.findAll()
				.stream()
				.map(d -> doctorConverter.fromEntity(d))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public DoctorDTO findById(Integer id) {
		Doctor doctor = doctorRepository.findById(id).get();
		return doctorConverter.fromEntity(doctor);
	}

	@Override
	@Transactional
	public DoctorDTO save(DoctorDTO doctorDTO) {
		Doctor doctor = doctorRepository.save(doctorConverter.fromDTO(doctorDTO));		
		return doctorConverter.fromEntity(doctor);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		doctorRepository.deleteById(id);
	}

}
