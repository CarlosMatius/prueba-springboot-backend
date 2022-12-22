package com.ingeneo.pruebaspringbootbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingeneo.pruebaspringbootbackend.entities.Almacenamiento;
import com.ingeneo.pruebaspringbootbackend.enums.TipoAlmacenamiento;

@Repository
public interface AlmacenamientoRepository extends JpaRepository<Almacenamiento, Long>{
	@Transactional(readOnly = true)
	List<Almacenamiento> findByTipo(TipoAlmacenamiento tipo);
	
	@Transactional(readOnly = true)
	Optional<Almacenamiento> findByNombre(String nombre);
}
