DBCC CHECKIDENT (paciente, RESEED, 0)
DBCC CHECKIDENT (medicos, RESEED, 0)
INSERT INTO registrocitas.dbo.medicos ([hora_fin_atencion],[anios_experiencia],[identificacion],[tipo_identificacion],[nombres],[tarjeta_profesional],[especialidad],[hora_inicio_atencion]) VALUES('17',7,'518916','CC','andres perez','123-AP','optometria','11')
INSERT INTO registrocitas.dbo.paciente ([eps_afiliacion],[fecha_nacimiento],[identificacion],[tipo_identificacion],[apellidos],[historia_clinica],[nombres]) VALUES ('compensar','1999-02-14','1598485','CC','perez','esta es la historia clinica','pepe')
