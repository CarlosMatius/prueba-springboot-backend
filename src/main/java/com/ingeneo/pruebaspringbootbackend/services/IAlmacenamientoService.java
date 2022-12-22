package com.ingeneo.pruebaspringbootbackend.services;

import java.util.List;

import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoDTO;
import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoRequest;
import com.ingeneo.pruebaspringbootbackend.enums.TipoAlmacenamiento;

public interface IAlmacenamientoService {
	
	void save(AlmacenamientoRequest almacenamientoRequest );
	List<AlmacenamientoDTO> findAll();
	List<AlmacenamientoDTO> findByTipo(TipoAlmacenamiento tipo);
	AlmacenamientoDTO findById(Long id);
	AlmacenamientoDTO findByNombre(String nombre);
	void udpdate(AlmacenamientoRequest almacenamientoRequest, Long id);
	void deleteById(Long id);
}
