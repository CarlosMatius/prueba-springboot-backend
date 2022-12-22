package com.ingeneo.pruebaspringbootbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingeneo.pruebaspringbootbackend.dto.ClienteDTO;
import com.ingeneo.pruebaspringbootbackend.dto.ClienteRequest;
import com.ingeneo.pruebaspringbootbackend.services.IClienteService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiBadRequest;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiInternalServerError;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiNotFound;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnprocessableEntity;
import com.ingeneo.pruebaspringbootbackend.validator.ClienteValidator;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ClienteControllers {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ClienteValidator clienteValidator;
	
	@PostMapping("/cliente/save")
	public ResponseEntity<Object> saveCliente(@RequestBody ClienteRequest clienteRequest) throws ApiInternalServerError, ApiUnprocessableEntity{
		clienteValidator.validarCampos(clienteRequest);
		clienteValidator.validarRegistro(clienteRequest);
		clienteService.save(clienteRequest);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@GetMapping("/cliente/all")
	public List<ClienteDTO> allClientes() {
		return clienteService.findAll();
	}
	
	@GetMapping("/cliente/by/id/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable String id) throws ApiBadRequest, ApiNotFound{
		clienteValidator.validarParametroId(id);
		clienteValidator.validarBusquedaPorId(id);
		return ResponseEntity.ok(clienteService.findById(Long.parseLong(id)));
	}
	
	@GetMapping("/cliente/by/nombre/{nombre}")
	public ResponseEntity<ClienteDTO> findByNombre(@PathVariable String nombre) throws ApiNotFound{
		clienteValidator.validarBusquedaPorNombre(nombre);
		return ResponseEntity.ok(clienteService.findByNombre(nombre));
	}
	
	@GetMapping("/cliente/by/cedula/{cedula}")
	public ResponseEntity<ClienteDTO> findByCedula(@PathVariable String cedula) throws ApiNotFound{
		clienteValidator.validarParametroCedula(cedula);
		clienteValidator.validarBusquedaPorCedula(cedula);
		return ResponseEntity.ok(clienteService.findByCedula(cedula));
	}
	
	@PutMapping("/cliente/update/{id}")
	public ResponseEntity<Object> updateCliente(@RequestBody ClienteRequest clienteRequest, @PathVariable String id) 
			throws ApiBadRequest, ApiNotFound, ApiInternalServerError, ApiUnprocessableEntity{
		clienteValidator.validarParametroId(id);
		clienteValidator.validarBusquedaPorId(id);
		clienteValidator.validarCampos(clienteRequest);
		clienteService.udpdate(clienteRequest, Long.parseLong(id));
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@DeleteMapping("cliente/delete/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable String id) throws ApiBadRequest, ApiNotFound{
		clienteValidator.validarParametroId(id);
		clienteValidator.validarBusquedaPorId(id);
		clienteService.deleteById(Long.parseLong(id));
		return ResponseEntity.ok(Boolean.TRUE);
	}

}
