package com.appointment.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Entity
@Table(name = "paciente")
@Getter
@Setter 
@NoArgsConstructor
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer idPatient;
    
	@Column(name = "nombres",length = 45)
    private String name;
	
	@Column(name = "apellidos",length = 45)
    private String lastname;
	
	@Column(name = "fecha_nacimiento")
    private LocalDate dateOfBirth;
    
    @Column(name = "identificacion",length = 45)
    private String identification;
    
    @Column(name = "tipo_identificacion",length = 45)
    private String identificationType;
    
    @Column(name = "eps_afiliacion",length = 45)
    private String affiliation;
    
    @Column(name = "historia_clinica",length = 2147483647)
    private String medicalHistory;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.LAZY )
    @JsonBackReference
    private List<Appointment> appointments;

}
