package com.ingeneo.pruebaspringbootbackend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApiUnprocessableEntity extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public ApiUnprocessableEntity(String message) {
		super(message);
	}

}
