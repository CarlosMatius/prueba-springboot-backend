package com.ingeneo.pruebaspringbootbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorJwtIOConfig implements WebMvcConfigurer {

	@Value("${ingeneo.jwt.security.enabled:false}")
	private boolean securityEnable;
	
	@Autowired
	private InterceptorJwtIO interceptorJwtIO;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(securityEnable) {
			registry.addInterceptor(interceptorJwtIO);
		}
	}
	
}
