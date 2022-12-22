package com.ingeneo.pruebaspringbootbackend.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.EntregaRequest;
import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;
import com.ingeneo.pruebaspringbootbackend.services.IEntregaService;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiBadRequest;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiInternalServerError;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiNotFound;
import com.ingeneo.pruebaspringbootbackend.utils.exceptions.ApiUnprocessableEntity;

@Service
public class EntregaValidator {
	
	@Autowired
	private IEntregaService entregaService;
	
	public void validarRegistro(EntregaRequest entregaRequest) throws ApiInternalServerError{
		if(entregaService.findByGuia(entregaRequest.getGuia()) != null) {
			throw new ApiInternalServerError("Ya existe un registro con esa guia");
		}
	}
	
	public void validarCampos(EntregaRequest entregaRequest) throws ApiUnprocessableEntity {
		
		Pattern placa = Pattern.compile("^[a-zA-Z]{3}[0-9]{3}$");
		Matcher matcherPlaca = placa.matcher(entregaRequest.getDato_vehiculo());
		
		Pattern flota = Pattern.compile("^[a-zA-Z]{3}[0-9]{4}[a-zA-Z]$");
		Matcher matcherFlota = flota.matcher(entregaRequest.getDato_vehiculo());
		
		if(entregaRequest.getTipo().isEmpty()) {
			throw new ApiUnprocessableEntity("Debes ingresar el tipo de envio");
		}
		
		if(!entregaRequest.getTipo().equals(TipoEnvio.MARITIMO.toString()) && 
			!entregaRequest.getTipo().equals(TipoEnvio.TERRESTRE.toString())) {
				throw new ApiUnprocessableEntity("Solo se permiten envios de tipo MARITIMO o TERRESTRE");
		}
		
		if(entregaRequest.getProducto().length()<3) {
			throw new ApiUnprocessableEntity("El nombre del producto es muy corto");
		}
		
		if(entregaRequest.getProducto().isEmpty()) {
			throw new ApiUnprocessableEntity("Debes ingresar el nombre del producto");
		}
		
		if(entregaRequest.getCantidad()<=0) {
			throw new ApiUnprocessableEntity("la cantidad no puede ser menro que 1");
		}
		
		if(entregaRequest.getFecha_entrega()==null) {
			throw new ApiUnprocessableEntity("Debes ingresar la fecha de entrega");
		}
		
		if(entregaRequest.getPrecio_envio().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ApiUnprocessableEntity("el precio del envio no puede ser menor a 1");
		}
		
		if(entregaRequest.getTipo().equals(TipoEnvio.MARITIMO.toString()) && matcherFlota.find()==false) {
			throw new ApiUnprocessableEntity("Numero de flota no valido");
		}
		
		if(entregaRequest.getTipo().equals(TipoEnvio.TERRESTRE.toString()) && matcherPlaca.find()==false) {
			throw new ApiUnprocessableEntity("Placa del vehiculo no valida");
		}
		
		if(entregaRequest.getGuia().isEmpty()) {
			throw new ApiUnprocessableEntity("Debes ingresar el numero de la guia");
		}
		
		if(entregaRequest.getGuia().length() !=10) {
			throw new ApiUnprocessableEntity("El numero de guia debe contener 10 digitos");
		}
		
	}
	
	public void validarParametroId(String id) throws ApiBadRequest {

		if (!id.matches("[0-9]*")) {
			throw new ApiBadRequest("Debes ingresar solo valores numericos");
		}
	}

	public void validarBusquedaPorId(String id) throws ApiNotFound {
		if(entregaService.findById(Long.parseLong(id)) == null) {
			throw new ApiNotFound("No hay registros con ese id");
		}
	}
	
	public void validarBusquedaPorGuia(String guia) throws ApiNotFound {
		if(entregaService.findByGuia(guia) ==null) {
			throw new ApiNotFound("No hay registros con esa guia");
		}
	}
	
	public void validarBusquedaPorTipo(String tipo) throws ApiUnprocessableEntity {
		if(!tipo.equals(TipoEnvio.MARITIMO.toString()) && !tipo.equals(TipoEnvio.TERRESTRE.toString())) {
			throw new ApiUnprocessableEntity("Solo se permiten busquedas de tipo MARITIMO o TERRESTRE");
		}
	}
}