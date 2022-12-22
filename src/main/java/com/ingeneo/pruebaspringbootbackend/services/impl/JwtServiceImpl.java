package com.ingeneo.pruebaspringbootbackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.JwtResponse;
import com.ingeneo.pruebaspringbootbackend.security.JwtIO;
import com.ingeneo.pruebaspringbootbackend.services.IJwtService;
import com.ingeneo.pruebaspringbootbackend.utils.date.DateUtils;

@Service
public class JwtServiceImpl implements IJwtService{

	@Autowired
	private JwtIO jwtIO;
	
	@Autowired
	private DateUtils dateUtils;
	
	@Value("${ingeneo.jwt.token.expires-in}")
	private int EXPIRES_IN;
	
	@Override
	public JwtResponse login(String clientId, String clientSecret) {
		// TODO Auto-generated method stub
		JwtResponse jwt = JwtResponse.builder()
				.tokenType("bearer")
				.accessToken(jwtIO.generateToken("matius"))
				.issuedAt(dateUtils.getDateMills()+"")
				.clientId(clientId)
				.expiresIn(EXPIRES_IN)
				.build();
		
		return jwt;
	}

}
