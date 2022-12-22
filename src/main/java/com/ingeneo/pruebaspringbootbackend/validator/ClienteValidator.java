package com.ingeneo.pruebaspringbootbackend.validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.ClienteRequest;
import com.ingeneo.pruebaspringbootbackend.services.IClienteService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiBadRequest;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiInternalServerError;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiNotFound;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnprocessableEntity;



@Service
public class ClienteValidator {

	@Autowired
	private IClienteService clienteService;
	
	public void validarRegistro(ClienteRequest clienteRequest) throws ApiInternalServerError {
		if(clienteService.findByCedula(clienteRequest.getCedula()) !=null) {
			throw new ApiInternalServerError("Ya existe un registro con ese numero de cedula");
		}
		
		if(clienteService.findByCorreo(clienteRequest.getCorreo()) !=null) {
			throw new ApiInternalServerError("Ya existe un registro con ese correo");
		}
		
	}
	
	public void validarCampos(ClienteRequest clienteRequest) throws ApiUnprocessableEntity {
		
		//patron para validar el correo
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(clienteRequest.getCorreo());
		
		if(clienteRequest.getNombre() == null || clienteRequest.getNombre().isEmpty()) {
			throw new ApiUnprocessableEntity("Debes ingresar un nombre");
		}
		
		if(clienteRequest.getNombre().length()<3) {
			throw new ApiUnprocessableEntity("El nombre es muy corto");
		}
		
		if(clienteRequest.getCedula().matches("[0-9]")) {
			throw new ApiUnprocessableEntity("El numero de cedula, no debe contener letras");
		}
		
		if(matcher.find() == false) {
			throw new ApiUnprocessableEntity("Ingresa un correo con un formato valido");
		}
		
		if(clienteRequest.getTelefono().matches("[0-9]")) {
			throw new ApiUnprocessableEntity("El numero de telefono, no debe contener letras");
		}
	
	}
	
	public void validarParametroId(String id) throws ApiBadRequest {
		if(!id.matches("[0-9]*")) {
			throw new ApiBadRequest("Debes ingresar solo valores numericos");
		}
	}
	
	public void validarBusquedaPorId(String id) throws ApiNotFound {
		if(clienteService.findById(Long.parseLong(id)) == null) {
			throw new ApiNotFound("No hay registros con ese id");
		}
	}
	
	public void validarBusquedaPorNombre(String nombre) throws ApiNotFound {
		if(clienteService.findByNombre(nombre) ==null) {
			throw new ApiNotFound("No hay registros con ese nombre");
		}
	}
	
	public void validarBusquedaPorCedula(String cedula) throws ApiNotFound {
		if(clienteService.findByCedula(cedula) ==null) {
			throw new ApiNotFound("No hay registros con ese numero de cedula");
		}
	}
	
	public void validarParametroCedula(String cedula) throws ApiNotFound {
		if(!cedula.matches("[0-9]*")) {
			throw new ApiNotFound("Debes ingresar solo valores numericos");
		}
	}
	
}
