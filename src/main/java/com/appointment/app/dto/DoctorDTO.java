package com.appointment.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author glzs
 *  DTO utilizado para devolver unicamente los datos necesarios
 *	para los doctores
 */
@Getter 
@Setter
public class DoctorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idDoctor;
	private String name;
	private String identificationType;
	private String identification;
	private String professionalCard;
	private BigDecimal experience;
	private String speciality;
	private String startSchedule;
	private String endSchedule;
	
}
