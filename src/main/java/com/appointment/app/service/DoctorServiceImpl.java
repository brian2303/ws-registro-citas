package com.appointment.app.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appointment.app.entity.Doctor;
import com.appointment.app.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Doctor> findById(Integer id) {
		return doctorRepository.findById(id);
	}

	@Override
	@Transactional
	public Doctor save(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		doctorRepository.deleteById(id);
	}

}
