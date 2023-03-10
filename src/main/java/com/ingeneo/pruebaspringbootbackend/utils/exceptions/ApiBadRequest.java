package com.ingeneo.pruebaspringbootbackend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiBadRequest extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ApiBadRequest(String message) {
		super(message);
	}
}
