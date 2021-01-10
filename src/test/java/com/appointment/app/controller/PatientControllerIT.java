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

import com.appointment.app.dto.PatientDTO;
import com.appointment.app.entity.Patient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private HttpHeaders headers;
	private HttpEntity<PatientDTO> entity;
	
	@Before
	public void setUp() {
		headers = new HttpHeaders();
	}
	
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/patient/clean-data.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/patient/clean-data.sql")
	public void testNewPatient() {
		entity = new HttpEntity<>(
					new PatientDTO("Daniela","Andrade",LocalDate.of(1991, Month.FEBRUARY,12),"564169","CE","Famisanar","Buena...."),
					headers
				);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/patients"),HttpMethod.POST,entity,String .class);
		List<PatientDTO> patients = jdbcTemplate.query(
				"SELECT nombres AS name,"
				+ "tipo_identificacion AS identificationType,"
				+ "identificacion AS identification,"
				+ "eps_afiliacion AS affiliation,"
				+ "fecha_nacimiento AS dateOfBirth,"
				+ "historia_clinica AS medicalHistory,"
				+ "apellidos AS lastname "
				+ "FROM paciente",
				new BeanPropertyRowMapper<>(PatientDTO.class)
			);
		assertEquals("Daniela",patients.get(0).getName());
		assertEquals("Andrade",patients.get(0).getLastname());
		assertEquals(LocalDate.of(1991,Month.FEBRUARY,12),patients.get(0).getDateOfBirth());
		assertEquals("564169",patients.get(0).getIdentification());
		assertEquals("CE",patients.get(0).getIdentificationType());
		assertEquals("Famisanar",patients.get(0).getAffiliation());
		assertEquals("Buena....",patients.get(0).getMedicalHistory());
		assertTrue(response.hasBody());
		assertEquals(201,response.getStatusCodeValue());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/patient/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/patient/clean-data.sql")
	public void testGetAllPatients() {
		ResponseEntity<Patient[]> result = restTemplate.getForEntity(
				createURLWithPort("/api/patients"),Patient[].class);
		Patient[] patients = result.getBody();
		assertNotNull(patients);
		assertEquals("6496641",patients[0].getIdentification());
		assertEquals("498416",patients[1].getIdentification());
		assertEquals("146163518",patients[2].getIdentification());
		assertEquals(3,patients.length,0);
		assertEquals(200,result.getStatusCodeValue());
	}
	
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/patient/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/patient/clean-data.sql")
	public void testUpdatePatient() {
		HttpEntity<PatientDTO> requestUpdate = new HttpEntity<>(
				new PatientDTO("Pepe","Perez",LocalDate.of(1991, Month.FEBRUARY,12),"564169","CE","Famisanar","Buena...."),headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/patients/1"),HttpMethod.PUT,requestUpdate,String.class);
		PatientDTO patient = jdbcTemplate.queryForObject("SELECT nombres AS name,"
				+ "tipo_identificacion AS identificationType,"
				+ "identificacion AS identification,"
				+ "eps_afiliacion AS affiliation,"
				+ "fecha_nacimiento AS dateOfBirth,"
				+ "historia_clinica AS medicalHistory,"
				+ "apellidos AS lastname "
				+ "FROM paciente WHERE id = 1", 
				new BeanPropertyRowMapper<>(PatientDTO.class));
		assertTrue(response.hasBody());
		assertEquals("Pepe",patient.getName());
		assertEquals("Perez",patient.getLastname());
		assertEquals(LocalDate.of(1991,Month.FEBRUARY,12),patient.getDateOfBirth());
		assertEquals("564169",patient.getIdentification());
		assertEquals("CE",patient.getIdentificationType());
		assertEquals("Famisanar",patient.getAffiliation());
		assertEquals("Buena....",patient.getMedicalHistory());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./script/patient/inserts.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "./script/patient/clean-data.sql")
	public void testDeletePatient() {
		PatientDTO patient = null;
		entity = new HttpEntity<>(null,headers);
		ResponseEntity<String> response  = restTemplate.exchange(
				createURLWithPort("/api/patients/1"),HttpMethod.DELETE,entity,String .class);
		try {
			patient = jdbcTemplate.queryForObject("SELECT nombres AS name,"
					+ "tipo_identificacion AS identificationType,"
					+ "identificacion AS identification,"
					+ "eps_afiliacion AS affiliation,"
					+ "fecha_nacimiento AS dateOfBirth,"
					+ "historia_clinica AS medicalHistory,"
					+ "apellidos AS lastname "
					+ "FROM paciente WHERE id = 1", 
					new BeanPropertyRowMapper<>(PatientDTO.class));	
		} catch (Exception e) {
			assertNull(patient);
			assertThatExceptionOfType(EmptyResultDataAccessException.class);
		}
		 
		assertFalse(response.hasBody());
	}
	
	
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
