package com.ingeneo.pruebaspringbootbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingeneo.pruebaspringbootbackend.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByNombre(String nombre);
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByCedula(String cedula);
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByCorreo(String correo);
	
}
