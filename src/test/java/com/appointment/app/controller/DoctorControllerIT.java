package com.appointment.app.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

import com.appointment.app.dto.DoctorDTO;
import com.appointment.app.entity.Doctor;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private HttpHeaders headers;
	private HttpEntity<DoctorDTO> entity;
	
	@Before
	public void setUp() {
		headers = new HttpHeaders();
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/doctor/clean-data.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/doctor/clean-data.sql")
	public void testNewDoctor() throws Exception {
		
		entity = new HttpEntity<>(new DoctorDTO("Andrea","CC","124792","1248-TD",5,"PED","8","17"),headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/doctors"),HttpMethod.POST,entity,String .class);
		List<DoctorDTO> doctors = jdbcTemplate.query(
				"SELECT nombres AS name,"
				+ "tipo_identificacion AS identificationType,"
				+ "identificacion AS identification,"
				+ "tarjeta_profesional AS professionalCard,"
				+ "anios_experiencia AS experience,"
				+ "especialidad AS speciality,"
				+ "hora_inicio_atencion AS startSchedule,"
				+ "hora_fin_atencion AS endSchedule "
				+ "FROM medicos",
				new BeanPropertyRowMapper<>(DoctorDTO.class)
			);
		
		assertEquals("Andrea",doctors.get(0).getName());
		assertEquals("CC",doctors.get(0).getIdentificationType());
		assertEquals("124792",doctors.get(0).getIdentification());
		assertEquals("1248-TD",doctors.get(0).getProfessionalCard());
		assertEquals(5,doctors.get(0).getExperience(),0.1);
		assertEquals("8",doctors.get(0).getStartSchedule());
		assertEquals("17",doctors.get(0).getEndSchedule());
		assertEquals(201, response.getStatusCodeValue());
		assertTrue(response.hasBody());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/doctor/insert.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/doctor/clean-data.sql")
	public void testGetAllDoctors() {
		ResponseEntity<Doctor[]> result = restTemplate.getForEntity(
				createURLWithPort("/api/doctors"),Doctor[].class);
		Doctor[] doctors = result.getBody();
		assertNotNull(doctors);
		assertEquals("518916",doctors[0].getIdentification());
		assertEquals("96185468",doctors[1].getIdentification());
		assertEquals("113443",doctors[2].getIdentification());
		assertEquals("56135689",doctors[3].getIdentification());
		assertEquals(200,result.getStatusCodeValue());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/doctor/insert.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/doctor/clean-data.sql")
	public void testUpdateDoctor() {
		
		HttpEntity<DoctorDTO> requestUpdate = new HttpEntity<>(
				new DoctorDTO("Andres","CE","5648156","1248-TD",5,"PED","8","17"), headers);
		
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/doctors/3"),HttpMethod.PUT,requestUpdate,String .class);
		
		DoctorDTO doctor = jdbcTemplate.queryForObject(
				"SELECT nombres AS name,"
				+ "tipo_identificacion AS identificationType,"
				+ "identificacion AS identification,"
				+ "tarjeta_profesional AS professionalCard,"
				+ "anios_experiencia AS experience,"
				+ "especialidad AS speciality,"
				+ "hora_inicio_atencion AS startSchedule,"
				+ "hora_fin_atencion AS endSchedule "
				+ "FROM medicos WHERE id=3",
				new BeanPropertyRowMapper<>(DoctorDTO.class));
		
		assertTrue(response.hasBody());
		assertEquals("Andres",doctor.getName());
		assertEquals("CE",doctor.getIdentificationType());
		assertEquals("5648156",doctor.getIdentification());
		assertEquals("1248-TD",doctor.getProfessionalCard());
		assertEquals(5,doctor.getExperience(),0.1);
		assertEquals("PED",doctor.getSpeciality());
		assertEquals("8",doctor.getStartSchedule());
		assertEquals("17",doctor.getEndSchedule());
		
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/doctor/insert.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/doctor/clean-data.sql")
	public void testDeleteDoctor() {
		DoctorDTO doctor = null;
		entity = new HttpEntity<>(null,headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/doctors/3"),HttpMethod.DELETE,entity,String .class);
		try {
			 doctor = jdbcTemplate.queryForObject(
					"SELECT nombres AS name,"
					+ "tipo_identificacion AS identificationType,"
					+ "identificacion AS identification,"
					+ "tarjeta_profesional AS professionalCard,"
					+ "anios_experiencia AS experience,"
					+ "especialidad AS speciality,"
					+ "hora_inicio_atencion AS startSchedule,"
					+ "hora_fin_atencion AS endSchedule "
					+ "FROM medicos WHERE id=3",
					new BeanPropertyRowMapper<>(DoctorDTO.class));	
		} catch (Exception e) {
			assertNull(doctor);
			assertThatExceptionOfType(EmptyResultDataAccessException.class);
		}
		assertFalse(response.hasBody());
	}
	
	
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}



