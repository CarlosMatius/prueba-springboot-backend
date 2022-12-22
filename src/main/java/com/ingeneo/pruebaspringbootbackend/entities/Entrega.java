package com.ingeneo.pruebaspringbootbackend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;

import lombok.Data;

@Data
@Entity
@Table(name = "entregas")
public class Entrega implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "enum('MARITIMO', 'TERRESTRE')")
	@Enumerated(EnumType.STRING)
	private TipoEnvio tipo;
	
	@Column(length = 45)
	private String producto;
	
	private int cantidad;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date fecha_registro;
	
	private Date fecha_entrega;
	
	@Column(precision = 11, scale = 2, length = 15)
    private BigDecimal precio_envio;
	
	// esta columna guarda la informacion del medio de transporte ya sea la placa o el numero de la flota
	@Column(length = 10)
	private String dato_vehiculo;
	
	@Column(length = 10, unique = true)
	private String guia;
	
	private BigDecimal descuento;
	
	@Column(precision = 11, scale = 2, length = 15)
    private BigDecimal precio_final;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "almacenamiento_id", nullable = false)
	private Almacenamiento almacenamiento;
	
	@PrePersist
    public void prePersist() {
        this.fecha_registro = new Date();
    }
	
	@PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
	
	public void realizarDescuento(BigDecimal porcentaje) {
		// la formula que se utilizara para sacar el porcentaje sera (porcentaje * precio envio) / 100
		
		// esta variable se utiliza para dividir el resultado entre 100 para asi tener el valor del porcentaje
		BigDecimal divisor = new BigDecimal("100");
		
		// esta variable se utliza para multiplicar el porcentaje por el valor del precio del envio
		BigDecimal resultadoMultiplicacion = porcentaje.multiply(getPrecio_envio());
		
		//luego se le asigna a la variable el valor del descuento
		setDescuento(resultadoMultiplicacion.divide(divisor));
		
		//se le asigna el valor al precio final, producto de la resta del precio del envio menos el valor del descuento
		setPrecio_final(getPrecio_envio().subtract(getDescuento()));
		
	}

}
