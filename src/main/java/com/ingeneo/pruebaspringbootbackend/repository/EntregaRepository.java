package com.ingeneo.pruebaspringbootbackend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingeneo.pruebaspringbootbackend.entities.Entrega;
import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{

	@Transactional(readOnly = true)
	List<Entrega> findByTipo(TipoEnvio tipo);
	
	@Transactional(readOnly = true)
	Optional<Entrega> findByGuia(String guia);
	
} 
