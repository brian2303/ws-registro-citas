package com.appointment.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
