package com.appointment.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "medicos")
@Getter @Setter @NoArgsConstructor
public class Doctor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idDoctor;
	
	@Column(name = "nombres",length = 45)
	private String name;
	
	@Column(name = "tipo_identificacion",length = 45)
    private String identificationType;
	
	@Column(name = "identificacion",length = 45)
	private String identification;
	
	@Column(name = "tarjeta_profesional",length = 45)
	private String professionalCard;
	
	@Column(name = "anios_experiencia",length = 45)
	private BigDecimal experience;
	
	@Column(name = "especialidad",length = 45)
    private String speciality;
	
	@Column(name = "hora_inicio_atencion",length = 45)
    private String startSchedule;
	
	@Column(name = "hora_fin_atencion",length = 45)
    private String endSchedule;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.LAZY )
	@JsonBackReference
	private List<Appointment> appointments;
	
}
