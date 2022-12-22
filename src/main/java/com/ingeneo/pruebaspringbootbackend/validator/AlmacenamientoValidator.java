package com.ingeneo.pruebaspringbootbackend.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoRequest;
import com.ingeneo.pruebaspringbootbackend.enums.TipoAlmacenamiento;
import com.ingeneo.pruebaspringbootbackend.services.IAlmacenamientoService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiBadRequest;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiInternalServerError;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiNotFound;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnprocessableEntity;

@Service
public class AlmacenamientoValidator {
	
	@Autowired
	private IAlmacenamientoService almacenamientoService;
	
	public void validarRegistro(AlmacenamientoRequest almacenamientoRequest) throws ApiInternalServerError {
		
		if(almacenamientoService.findByNombre(almacenamientoRequest.getNombre()) !=null) {
			throw new ApiInternalServerError("Ya existe un registro con ese nombre");
		}
	}
	
	public void validarCampos(AlmacenamientoRequest almacenamientoRequest) throws ApiUnprocessableEntity {
		if(almacenamientoRequest.getTipo().isEmpty()) {
			throw new ApiUnprocessableEntity("Debes ingresar el tipo de almacenamiento");
		}
		
		if(!almacenamientoRequest.getTipo().equals(TipoAlmacenamiento.BODEGA.toString()) && 
			!almacenamientoRequest.getTipo().equals(TipoAlmacenamiento.PUERTO.toString())) {
			throw new ApiUnprocessableEntity("Solo se permiten almacenamientos de tipo BODEGA o PUERTO");
		}
		
		if(almacenamientoRequest.getNombre() == null || almacenamientoRequest.getNombre().isEmpty()) {
			throw new ApiUnprocessableEntity("Debes ingresar un nombre");
		}
		
		if(almacenamientoRequest.getNombre().length()<3) {
			throw new ApiUnprocessableEntity("El nombre es muy corto");
		}
	}

	public void validarParametroId(String id) throws ApiBadRequest {
		
		if(!id.matches("[0-9]*")) {
			throw new ApiBadRequest("Debes ingresar solo valores numericos");
		}
	}
	
	public void validarBusquedaPorId(String id) throws ApiNotFound {
		if(almacenamientoService.findById(Long.parseLong(id)) == null) {
			throw new ApiNotFound("No hay registros con ese id");
		}
	}
	
	public void validarBusquedaPorTipo(String tipo) throws ApiUnprocessableEntity {
		if(!tipo.equals(TipoAlmacenamiento.BODEGA.toString()) && !tipo.equals(TipoAlmacenamiento.PUERTO.toString())) {
			throw new ApiUnprocessableEntity("Solo se permiten almacenamientos de tipo BODEGA o PUERTO");
		}
	}
	
	public void validarBusquedaPorNombre(String nombre) throws ApiNotFound {
		if(almacenamientoService.findByNombre(nombre) ==null) {
			throw new ApiNotFound("No hay registros con ese nombre");
		}
	}
}
