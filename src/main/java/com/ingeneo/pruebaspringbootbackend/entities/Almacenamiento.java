package com.ingeneo.pruebaspringbootbackend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ingeneo.pruebaspringbootbackend.enums.TipoAlmacenamiento;

import lombok.Data;

@Data
@Entity
@Table(name = "almacenamientos")
public class Almacenamiento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "enum('BODEGA', 'PUERTO')")
	@Enumerated(EnumType.STRING)
	private TipoAlmacenamiento tipo;
	
	@Column(length = 45, unique = true)
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
	@Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
	@PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "almacenamiento", cascade = CascadeType.ALL)
    private List<Entrega> entregas;

}
