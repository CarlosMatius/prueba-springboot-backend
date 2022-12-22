package com.ingeneo.pruebaspringbootbackend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorizazed extends Exception{

private static final long serialVersionUID = 1L;
	
	public ApiUnauthorizazed(String message) {
		super(message);
	}
}
