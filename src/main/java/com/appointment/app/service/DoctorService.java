package com.appointment.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.app.dto.DoctorDTO;
@Service
public interface DoctorService {
	
	public List<DoctorDTO> findAll();
	
	public DoctorDTO findById(Integer id);
	
	public DoctorDTO save(DoctorDTO doctorDTO);
	
	public void deleteById(Integer id);
}
