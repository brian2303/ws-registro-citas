package com.appointment.app.converter;

public interface Converter<E,D> {
	E fromDTO(D dto);
	D fromEntity(E entity);
}
