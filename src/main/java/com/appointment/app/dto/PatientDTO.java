package com.appointment.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author glzs
 * DTO utilizado para devolver unicamente los datos necesarios
 *	para la solicitud de pacientes
 */
@Getter
@Setter
@NoArgsConstructor
public class PatientDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idPatient;
	private String name;
	private String lastname;
	private LocalDate dateOfBirth;
	private String identification;
	private String identificationType;
	private String affiliation;
	private String medicalHistory;

}
