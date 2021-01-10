package com.appointment.app.converter;

public interface Converter<E,D> {
	
	/**
	 * Utilizado para convertir un Dto a un entity
	 * para finalmente poder persistir la información 
	 * en una fuente de almacenamiento.
	 * 
	 * @param dto utilizado para transformar a entity
	 * @return un entity Definido en la implementación
	 */
	E fromDTO(D dto);
	
	/**
	 * utilizado para convertir de un Entity a un objeto Dto 
	 * unicamente con los datos necesarios para el cliente
	 * 
	 * @param entity utilizado para transformar a DTO 
	 * @return un DTO definido en la implementación
	 */
	D fromEntity(E entity);
}
