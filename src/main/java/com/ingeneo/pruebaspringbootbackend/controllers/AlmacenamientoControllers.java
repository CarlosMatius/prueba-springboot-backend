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

import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoDTO;
import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoRequest;
import com.ingeneo.pruebaspringbootbackend.enums.TipoAlmacenamiento;
import com.ingeneo.pruebaspringbootbackend.services.IAlmacenamientoService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiBadRequest;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiInternalServerError;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiNotFound;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnprocessableEntity;
import com.ingeneo.pruebaspringbootbackend.validator.AlmacenamientoValidator;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AlmacenamientoControllers {

	@Autowired
	private IAlmacenamientoService almacenamientoService;
	
	@Autowired
	private AlmacenamientoValidator almacenamientoValidator;
	
	@PostMapping("/almacenamiento/save")
	public ResponseEntity<Object> saveAlmacenamiento(@RequestBody AlmacenamientoRequest almacenamientoRequest) throws ApiInternalServerError, ApiUnprocessableEntity{
		almacenamientoValidator.validarCampos(almacenamientoRequest);
		almacenamientoValidator.validarRegistro(almacenamientoRequest);
		almacenamientoService.save(almacenamientoRequest);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@GetMapping("/almacenamiento/all")
	public List<AlmacenamientoDTO> allAlmacenamientos() {
		return almacenamientoService.findAll();
	}
	
	@GetMapping("/almacenamiento/by/id/{id}")
	public ResponseEntity<AlmacenamientoDTO> fingById(@PathVariable String id) throws ApiBadRequest, ApiNotFound {
		almacenamientoValidator.validarParametroId(id);
		almacenamientoValidator.validarBusquedaPorId(id);
		return ResponseEntity.ok(almacenamientoService.findById(Long.parseLong(id)));
	}
	
	@GetMapping("/almacenamiento/by/tipo/{tipo}")
	public List<AlmacenamientoDTO> findByTipo(@PathVariable String tipo) throws ApiUnprocessableEntity {
		almacenamientoValidator.validarBusquedaPorTipo(tipo);
		return almacenamientoService.findByTipo(TipoAlmacenamiento.valueOf(tipo));
	}
	
	@GetMapping("/almacenamiento/by/nombre/{nombre}")
	public ResponseEntity<AlmacenamientoDTO> findByNombre(@PathVariable String nombre) throws ApiNotFound {
		almacenamientoValidator.validarBusquedaPorNombre(nombre);
		return ResponseEntity.ok(almacenamientoService.findByNombre(nombre));
	}
	
	@PutMapping("almacenamiento/update/{id}")
	public ResponseEntity<Object> updateAlmacenamiento(@RequestBody AlmacenamientoRequest almacenamientoRequest, @PathVariable String id)
			throws ApiBadRequest, ApiNotFound, ApiUnprocessableEntity {
		almacenamientoValidator.validarParametroId(id);
		almacenamientoValidator.validarBusquedaPorId(id);
		almacenamientoValidator.validarCampos(almacenamientoRequest);
		almacenamientoService.udpdate(almacenamientoRequest, Long.parseLong(id));
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@DeleteMapping("/almacenamiento/delete/{id}")
	public ResponseEntity<Object> deleteAlmacenamiento(@PathVariable String id) throws ApiBadRequest, ApiNotFound {
		almacenamientoValidator.validarParametroId(id);
		almacenamientoValidator.validarBusquedaPorId(id);
		almacenamientoService.deleteById(Long.parseLong(id));
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
