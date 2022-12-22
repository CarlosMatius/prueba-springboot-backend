package com.ingeneo.pruebaspringbootbackend.services;

import java.util.List;

import com.ingeneo.pruebaspringbootbackend.dto.EntregaDTO;
import com.ingeneo.pruebaspringbootbackend.dto.EntregaRequest;
import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;

public interface IEntregaService {
	
	void save(EntregaRequest entregaRequest);
	List<EntregaDTO> findAll();
	List<EntregaDTO> findByTipo(TipoEnvio tipo);
	EntregaDTO findById(Long id);
	EntregaDTO findByGuia(String guia);
	void update(EntregaRequest entregaRequest, Long id);
	void deleteById(Long id);	
}
