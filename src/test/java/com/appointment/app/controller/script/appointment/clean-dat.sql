DELETE FROM citas
DBCC CHECKIDENT (citas, RESEED, 0)
DELETE FROM medicos
DBCC CHECKIDENT (medicos, RESEED, 0)
DELETE FROM paciente
DBCC CHECKIDENT (paciente, RESEED, 0)

