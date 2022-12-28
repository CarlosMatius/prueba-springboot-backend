package com.ingeneo.pruebaspringbootbackend.dto;


import java.util.Date;

import lombok.Data;

@Data
public class AlmacenamientoDTO{

	private Long id;
	private String tipo;
	private String nombre;
	private Date createdAt;
	private Date updatedAt;
}
