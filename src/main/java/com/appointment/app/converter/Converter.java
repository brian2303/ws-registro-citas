package com.appointment.app.converter;

public interface Converter<E,D> {
	
	/**
	 * 
	 * @param dto utilizado para transformar a entity
	 * @return un entity Definido en la implementación
	 */
	E fromDTO(D dto);
	
	/**
	 * 
	 * @param entity utilizado para transformar a DTO 
	 * @return un DTO definido en la implementación
	 */
	D fromEntity(E entity);
}
