package com.ingeneo.pruebaspringbootbackend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Excepecion personalizada de status 404

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public ApiNotFound(String message) {
		super(message);
	}

}
