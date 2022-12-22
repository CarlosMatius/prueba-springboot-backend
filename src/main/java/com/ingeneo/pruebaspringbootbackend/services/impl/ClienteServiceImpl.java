package com.ingeneo.pruebaspringbootbackend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.ClienteDTO;
import com.ingeneo.pruebaspringbootbackend.dto.ClienteRequest;
import com.ingeneo.pruebaspringbootbackend.entities.Cliente;
import com.ingeneo.pruebaspringbootbackend.repository.ClienteRepository;
import com.ingeneo.pruebaspringbootbackend.services.IClienteService;
import com.ingeneo.pruebaspringbootbackend.utils.configurations.ModelMapperSpring;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void save(ClienteRequest clienteRequest) {
		// TODO Auto-generated method stub
		Cliente cliente = ModelMapperSpring.modelMapper().map(clienteRequest, Cliente.class);
		clienteRepository.save(cliente);
	}

	@Override
	public List<ClienteDTO> findAll() {
		// TODO Auto-generated method stub
		List<ClienteDTO> dtoList = new ArrayList<>();
		Iterable<Cliente> clientes = clienteRepository.findAll();
		for(Cliente cliente : clientes) {
			ClienteDTO clienteDTO = ModelMapperSpring.modelMapper().map(cliente, ClienteDTO.class);
			dtoList.add(clienteDTO);
		}
		return dtoList;
	}

	@Override
	public ClienteDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(!cliente.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(cliente.get(), ClienteDTO.class);
	}

	@Override
	public ClienteDTO findByNombre(String nombre) {
		// TODO Auto-generated method stub
		Optional<Cliente> cliente = clienteRepository.findByNombre(nombre);
		if(!cliente.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(cliente.get(), ClienteDTO.class);
	}

	@Override
	public ClienteDTO findByCedula(String cedula) {
		// TODO Auto-generated method stub
		Optional<Cliente> cliente = clienteRepository.findByCedula(cedula);
		if(!cliente.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(cliente.get(), ClienteDTO.class);
	}
	
	@Override
	public ClienteDTO findByCorreo(String correo) {
		// TODO Auto-generated method stub
		Optional<Cliente> cliente = clienteRepository.findByCorreo(correo);
		if(!cliente.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(cliente.get(), ClienteDTO.class);
	}

	@Override
	public void udpdate(ClienteRequest clienteRequest, Long id) {
		// TODO Auto-generated method stub
		Optional<Cliente> clientes = clienteRepository.findById(id);
		if(clientes.isPresent()) {
			Cliente cliente = clientes.get();
			cliente.setNombre(clienteRequest.getNombre());
			cliente.setCedula(clienteRequest.getCedula());
			cliente.setCorreo(clienteRequest.getCorreo());
			cliente.setTelefono(clienteRequest.getTelefono());
			clienteRepository.save(cliente);
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

}
