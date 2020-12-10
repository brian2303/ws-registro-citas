package com.appointment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.app.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {}
