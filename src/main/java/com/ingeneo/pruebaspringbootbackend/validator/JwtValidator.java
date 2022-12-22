package com.ingeneo.pruebaspringbootbackend.validator;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnauthorizazed;


@Service
public class JwtValidator {

	private static final String CLIENT_CREDENTIALS = "client_credentials";
	
	public void validateCredentials(MultiValueMap<String, String> parmMap, String grantType) throws ApiUnauthorizazed {
		// TODO Auto-generated method stub
		if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)) {
			throw new ApiUnauthorizazed("el campo gran type es invalido");
		}
		if(Objects.isNull(parmMap) || parmMap.getFirst("client_id").isEmpty() || parmMap.getFirst("client_secret").isEmpty()) {
			throw new ApiUnauthorizazed("client_id y/o client_secret son invalidos");
		}
		
	}
}
