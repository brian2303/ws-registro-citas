package com.appointment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.app.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {}
