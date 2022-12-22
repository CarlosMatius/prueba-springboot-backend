package com.ingeneo.pruebaspringbootbackend.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ingeneo.pruebaspringbootbackend.entities.Almacenamiento;
import com.ingeneo.pruebaspringbootbackend.entities.Cliente;

import lombok.Data;

@Data
public class EntregaRequest {
	
	private String tipo;
	private String producto;
	private int cantidad;
	private Date fecha_entrega;
	private BigDecimal precio_envio;
	private String dato_vehiculo;
	private String guia;
	private Cliente cliente;
	private Almacenamiento almacenamiento;
}
