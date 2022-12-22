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

import com.ingeneo.pruebaspringbootbackend.dto.EntregaDTO;
import com.ingeneo.pruebaspringbootbackend.dto.EntregaRequest;
import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;
import com.ingeneo.pruebaspringbootbackend.services.IEntregaService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiBadRequest;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiInternalServerError;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiNotFound;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnprocessableEntity;
import com.ingeneo.pruebaspringbootbackend.validator.EntregaValidator;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class EntregaControllers {
	
	@Autowired
	private IEntregaService entregaService;
	
	@Autowired
	private EntregaValidator entregaValidator;


	@PostMapping("/entrega/save")
	public ResponseEntity<Object> saveEntrega(@RequestBody EntregaRequest entregaRequest) throws ApiInternalServerError, ApiUnprocessableEntity{
		entregaValidator.validarRegistro(entregaRequest);
		entregaValidator.validarCampos(entregaRequest);
		entregaService.save(entregaRequest);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@GetMapping("/entrega/all")
	public List<EntregaDTO> allEntregas() {
		return entregaService.findAll();
	}
	
	@GetMapping("/entrega/by/id/{id}")
	public ResponseEntity<EntregaDTO> fingById(@PathVariable String id) throws ApiBadRequest, ApiNotFound {
		entregaValidator.validarParametroId(id);
		entregaValidator.validarBusquedaPorId(id);
		return ResponseEntity.ok(entregaService.findById(Long.parseLong(id)));
	}
	
	@GetMapping("/entrega/by/tipo/{tipo}")
	public List<EntregaDTO> findByTipo(@PathVariable String tipo) throws ApiUnprocessableEntity {
		entregaValidator.validarBusquedaPorTipo(tipo);
		return entregaService.findByTipo(TipoEnvio.valueOf(tipo));
	}
	
	@GetMapping("/entrega/by/guia/{guia}")
	public ResponseEntity<EntregaDTO> findByGuia(@PathVariable String guia) throws ApiNotFound {
		entregaValidator.validarBusquedaPorGuia(guia);
		return ResponseEntity.ok(entregaService.findByGuia(guia));
	}
	
	@PutMapping("entrega/update/{id}")
	public ResponseEntity<Object> updateEntrega(@RequestBody EntregaRequest entregaRequest, @PathVariable String id)
			throws ApiBadRequest, ApiNotFound, ApiUnprocessableEntity {
		entregaValidator.validarParametroId(id);
		entregaValidator.validarBusquedaPorId(id);
		entregaValidator.validarCampos(entregaRequest);
		entregaService.update(entregaRequest, Long.parseLong(id));
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@DeleteMapping("/entregas/delete/{id}")
	public ResponseEntity<Object> deleteEntrega(@PathVariable String id) throws ApiBadRequest, ApiNotFound {
		entregaValidator.validarParametroId(id);
		entregaValidator.validarBusquedaPorId(id);
		entregaService.deleteById(Long.parseLong(id));
		return ResponseEntity.ok(Boolean.TRUE);
	}
}


