package com.appointment.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author glzs
 * DTO utilizado para poder recibir los datos para la comprobación
 * de citas por paciente y por doctor en una fecha determinada.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentAvailableDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDate date;
	
}
