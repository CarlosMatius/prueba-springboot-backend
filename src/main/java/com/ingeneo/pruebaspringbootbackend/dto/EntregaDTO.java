package com.ingeneo.pruebaspringbootbackend.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;

import lombok.Data;

@Data
public class EntregaDTO {


	private Long id;
	private TipoEnvio tipo;
	private String producto;
	private int cantidad;
	private Date fecha_registro;
	private Date fecha_entrega;
	private BigDecimal precio_envio;
	private String dato_vehiculo;
	private String guia;
	private BigDecimal descuento;
	private BigDecimal precio_final;
	private Date updatedAt;
	private ClienteDTO cliente;
	private AlmacenamientoDTO almacenamiento;
	
}
