package com.appointment.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.appointment.app.entity.Doctor;

@Service
public interface DoctorService {
	
	public List<Doctor> findAll();
	
	public Optional<Doctor> findById(Integer id);
	
	public Doctor save(Doctor doctor);
	
	public void deleteById(Integer id);
}
