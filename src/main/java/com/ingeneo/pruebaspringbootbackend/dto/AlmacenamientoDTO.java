package com.ingeneo.pruebaspringbootbackend.dto;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AlmacenamientoDTO {

	private Long id;
	private String tipo;
	private String nombre;
	private Date createdAt;
	private Date updateAt;
	private List<EntregaDTO> entregas;
}
