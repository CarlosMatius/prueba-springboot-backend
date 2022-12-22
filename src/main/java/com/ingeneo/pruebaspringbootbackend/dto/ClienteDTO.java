package com.ingeneo.pruebaspringbootbackend.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ClienteDTO {

	private int id;
	private String nombre;
	private String cedula;
	private String correo;
	private String telefono;
	private Date createdAt;
	private Date updateAt;
	private List<EntregaDTO> entregas;
	
}
