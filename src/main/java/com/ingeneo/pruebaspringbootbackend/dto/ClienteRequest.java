package com.ingeneo.pruebaspringbootbackend.dto;

import lombok.Data;

@Data
public class ClienteRequest {

	private String nombre;
	private String cedula;
	private String correo;
	private String telefono;
}
