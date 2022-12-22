package com.ingeneo.pruebaspringbootbackend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiInternalServerError extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public ApiInternalServerError(String message) {
		super(message);
	}

}
