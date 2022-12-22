package com.ingeneo.pruebaspringbootbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingeneo.pruebaspringbootbackend.dto.LoginRequest;
import com.ingeneo.pruebaspringbootbackend.services.IJwtService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnauthorizazed;

@RestController
@RequestMapping(path = "v1.0")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthControllers {
	
	@Autowired
	private IJwtService jwtService;
	
	@PostMapping(path = "oauth/client_credential/accesstoken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginRequest paramMap) throws ApiUnauthorizazed {
		return ResponseEntity.ok(jwtService.login(paramMap.getClientId(), paramMap.getClientSecret()));
	}

}
