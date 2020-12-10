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



@Entity
@Table(name = "medicos")
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Appointment> appointments;
	
	public Doctor() {}

	public Integer getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Integer idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getProfessionalCard() {
		return professionalCard;
	}

	public void setProfessionalCard(String professionalCard) {
		this.professionalCard = professionalCard;
	}

	public BigDecimal getExperience() {
		return experience;
	}

	public void setExperience(BigDecimal experience) {
		this.experience = experience;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getStartSchedule() {
		return startSchedule;
	}

	public void setStartSchedule(String startSchedule) {
		this.startSchedule = startSchedule;
	}

	public String getEndSchedule() {
		return endSchedule;
	}

	public void setEndSchedule(String endSchedule) {
		this.endSchedule = endSchedule;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
