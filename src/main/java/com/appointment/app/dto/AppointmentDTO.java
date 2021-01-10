package com.appointment.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author glzs
 *	DTO utilizado para devolver unicamente los datos necesarios
 *	para la solicitud de Citas
 */
@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idAppointment;
	private LocalDate appointmentDate;
	private String appointmentHour;
	private Integer idDoctor;
	private String nameDoctor;
	private Integer idPatient;
	private String namePatient;
	
	public AppointmentDTO(LocalDate appointmentDate, String appointmentHour, Integer idDoctor,
			Integer idPatient) {
		super();
		this.appointmentDate = appointmentDate;
		this.appointmentHour = appointmentHour;
		this.idDoctor = idDoctor;
		this.idPatient = idPatient;
	}
	
	
}
