package com.appointment.app.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.appointment.app.dto.AppointmentAvailableDTO;
import com.appointment.app.dto.AppointmentDTO;
import com.appointment.app.entity.Appointment;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentControllerIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private HttpHeaders headers;
	private HttpEntity<AppointmentDTO> entity;
	
	@Before
	public void setUp() {
		headers = new HttpHeaders();
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/appointment/pre-data.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/appointment/clean-dat.sql")
	public void testNewAppointment() {
		entity = new HttpEntity<>(new AppointmentDTO(LocalDate.of(2021, 12, 15),"8",1,1),headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("api/appointments"),HttpMethod.POST,entity,String.class);
		List<AppointmentDTO> appointments = jdbcTemplate.query(
				"SELECT id AS idAppointment,"
				+ "fecha_cita AS appointmentDate,"
				+ "hora_cita AS appointmentHour,"
				+ "idmedicos AS idDoctor,"
				+ "idpaciente AS idPatient "
				+ "FROM citas",
				new BeanPropertyRowMapper<>(AppointmentDTO.class)
			);
		assertEquals(appointments.get(0).getAppointmentDate(),LocalDate.of(2021, 12, 15));
		assertEquals(appointments.get(0).getAppointmentHour(),"8");
		assertEquals(201, response.getStatusCodeValue());
		assertTrue(response.hasBody());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/appointment/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/appointment/clean-dat.sql")
	public void testGetAllAppointments() {
		ResponseEntity<Appointment[]> result = restTemplate.getForEntity(
				createURLWithPort("/api/appointments"),Appointment[].class);
		Appointment[] appointments = result.getBody();
		assertNotNull(appointments);
		assertEquals("10",appointments[0].getAppointmentHour());
		assertEquals("11",appointments[1].getAppointmentHour());
		assertEquals("12",appointments[2].getAppointmentHour());
		assertEquals("16",appointments[3].getAppointmentHour());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/appointment/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/appointment/clean-dat.sql")
	public void testUpdateAppointment() {
		entity = new HttpEntity<>(new AppointmentDTO(LocalDate.of(2021, 12, 17),"10",1,1),headers);
		ResponseEntity<String> response  = restTemplate.exchange(createURLWithPort("/api/appointments/2"),HttpMethod.PUT,entity,String .class);
		AppointmentDTO appointment = jdbcTemplate.queryForObject(
				"SELECT id AS idAppointment,"
						+ "fecha_cita AS appointmentDate,"
						+ "hora_cita AS appointmentHour,"
						+ "idmedicos AS idDoctor,"
						+ "idpaciente AS idPatient "
						+ "FROM citas WHERE id=2",
				new BeanPropertyRowMapper<>(AppointmentDTO.class));
		assertTrue(response.hasBody());
		assertEquals(200,response.getStatusCodeValue());
		assertEquals("10",appointment.getAppointmentHour());
		assertEquals(LocalDate.of(2021, 12, 17),appointment.getAppointmentDate());
		assertEquals(1,appointment.getIdDoctor(),0.1);
		assertEquals(1,appointment.getIdPatient(),0.1);
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/appointment/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/appointment/clean-dat.sql")
	public void testDeleteAppointment() {
		AppointmentDTO appointment = null;
		entity = new HttpEntity<>(null,headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/appointments/2"),HttpMethod.DELETE,entity,String .class);
		try {
			appointment = jdbcTemplate.queryForObject(
					"SELECT id AS idAppointment,"
							+ "fecha_cita AS appointmentDate,"
							+ "hora_cita AS appointmentHour,"
							+ "idmedicos AS idDoctor,"
							+ "idpaciente AS idPatient "
							+ "FROM citas WHERE id=2",
					new BeanPropertyRowMapper<>(AppointmentDTO.class));	
		} catch (Exception e) {
			assertNull(appointment);
			assertThatExceptionOfType(EmptyResultDataAccessException.class);
		}
		
		assertFalse(response.hasBody());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/appointment/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/appointment/clean-dat.sql")
	public void testAvailableAppointmentsByDoctor() {
		HttpEntity<AppointmentAvailableDTO> entity = new HttpEntity<>(
				new AppointmentAvailableDTO(1,LocalDate.of(2021, Month.JANUARY, 05)),headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("api/appointments/available"),HttpMethod.POST,entity,String.class);
		assertTrue(response.hasBody());
		assertFalse(response.getBody().contains("11"));
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/appointment/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/appointment/clean-dat.sql")
	public void testQuantityAppointmentByPatient(){
		HttpEntity<AppointmentAvailableDTO> entity = new HttpEntity<>(
				new AppointmentAvailableDTO(1,LocalDate.of(2021, Month.JANUARY, 05)),headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("api/appointments/quantity"),HttpMethod.POST,entity,String.class);
		assertTrue(response.hasBody());
		assertEquals(1, Double.parseDouble(response.getBody()),0);
	}
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}








