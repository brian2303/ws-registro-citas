package com.appointment.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citas")
public class Appointment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer idAppointment;
    
	@Column(name = "fecha_cita")
    private LocalDate appointmentDate;
    
    @Column(name = "hora_cita",length = 45)
    private String appointmentHour;
    
    @JoinColumn(name = "idmedicos", referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Doctor doctor;
    
    @JoinColumn(name = "idpaciente", referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Patient patient;

	public Integer getIdAppointment() {
		return idAppointment;
	}

	public void setIdAppointment(Integer idAppointment) {
		this.idAppointment = idAppointment;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentHour() {
		return appointmentHour;
	}

	public void setAppointmentHour(String appointmentHour) {
		this.appointmentHour = appointmentHour;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
