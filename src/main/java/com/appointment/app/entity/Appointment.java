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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "citas")
@Getter @Setter @NoArgsConstructor
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
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Doctor doctor;
    
    @JoinColumn(name = "idpaciente", referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Patient patient;

}
