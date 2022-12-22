package com.ingeneo.pruebaspringbootbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequest {

	@JsonProperty(value = "client_id")
	private String clientId;
	
	@JsonProperty(value = "client_secret")
	private String clientSecret;
}
