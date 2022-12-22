package com.ingeneo.pruebaspringbootbackend.services;

import com.ingeneo.pruebaspringbootbackend.dto.JwtResponse;

public interface IJwtService {

	JwtResponse login(String clientId, String clientSecret);
}
