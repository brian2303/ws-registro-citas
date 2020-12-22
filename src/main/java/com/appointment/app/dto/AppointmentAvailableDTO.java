package com.appointment.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentAvailableDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDate date;
}
