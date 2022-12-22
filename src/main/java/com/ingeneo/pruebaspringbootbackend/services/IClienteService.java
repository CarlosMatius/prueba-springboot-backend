package com.ingeneo.pruebaspringbootbackend.services;

import java.util.List;

import com.ingeneo.pruebaspringbootbackend.dto.ClienteDTO;
import com.ingeneo.pruebaspringbootbackend.dto.ClienteRequest;

public interface IClienteService {
	
	void save(ClienteRequest clienteRequest);
	List<ClienteDTO> findAll();
	ClienteDTO findById(Long id);
	ClienteDTO findByNombre(String nombre);
	ClienteDTO findByCedula(String cedula);
	ClienteDTO findByCorreo(String correo);
	void udpdate(ClienteRequest clienteRequest, Long id);
	void deleteById(Long id);
}
