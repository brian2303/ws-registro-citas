package com.appointment.app.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author glzs
 *  DTO utilizado para devolver unicamente los datos necesarios
 *	para los doctores
 */
@Getter 
@Setter
@NoArgsConstructor
public class DoctorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idDoctor;
	private String name;
	private String identificationType;
	private String identification;
	private String professionalCard;
	private Integer experience;
	private String speciality;
	private String startSchedule;
	private String endSchedule;
	
	public DoctorDTO(String name, String identificationType, String identification, String professionalCard,
			Integer experience, String speciality, String startSchedule, String endSchedule) {
		super();
		this.name = name;
		this.identificationType = identificationType;
		this.identification = identification;
		this.professionalCard = professionalCard;
		this.experience = experience;
		this.speciality = speciality;
		this.startSchedule = startSchedule;
		this.endSchedule = endSchedule;
	}
	
	
	
}
