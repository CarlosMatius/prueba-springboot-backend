package com.ingeneo.pruebaspringbootbackend.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.EntregaDTO;
import com.ingeneo.pruebaspringbootbackend.dto.EntregaRequest;
import com.ingeneo.pruebaspringbootbackend.entities.Entrega;
import com.ingeneo.pruebaspringbootbackend.enums.TipoEnvio;
import com.ingeneo.pruebaspringbootbackend.repository.EntregaRepository;
import com.ingeneo.pruebaspringbootbackend.services.IEntregaService;
import com.ingeneo.pruebaspringbootbackend.utils.configurations.ModelMapperSpring;

@Service
public class EntregaServiceImpl implements IEntregaService{
	
	@Autowired
	private EntregaRepository entregaRepository;

	@Override
	public void save(EntregaRequest entregaRequest) {
		Entrega entrega = ModelMapperSpring.modelMapper().map(entregaRequest, Entrega.class);
		if(entregaRequest.getTipo().equals("TERRESTRE") && entregaRequest.getCantidad()>10) {
			entrega.realizarDescuento(new BigDecimal("5"));
		}
		
		if (entregaRequest.getTipo().equals("MARITIMO") && entregaRequest.getCantidad() > 10) {
			entrega.realizarDescuento(new BigDecimal("3"));
		}
	
		if(entregaRequest.getCantidad()<=10) {
			// si no se cumple la condicion para establecer un descuento se le asigna 0
			entrega.setDescuento(new BigDecimal("0"));
			
			// le asignamos el valor del envio al precio final
			entrega.setPrecio_final(entregaRequest.getPrecio_envio());
		}
		entregaRepository.save(entrega);
	
	}
	
	@Override
	public List<EntregaDTO> findAll() {
		// TODO Auto-generated method stub
		List<EntregaDTO> dtoList = new ArrayList<>();
		Iterable<Entrega> entregas = entregaRepository.findAll();
		for(Entrega entrega: entregas) {
			EntregaDTO entregaDTO = ModelMapperSpring.modelMapper().map(entrega, EntregaDTO.class);
			dtoList.add(entregaDTO);
		}
		return dtoList;
	}
	
	
	@Override
	public List<EntregaDTO> findByTipo(TipoEnvio tipo) {
		// TODO Auto-generated method stub
		List<EntregaDTO> dtoList = new ArrayList<>();
		Iterable<Entrega> entregas = entregaRepository.findByTipo(tipo);
		for(Entrega entrega: entregas) {
			EntregaDTO entregaDTO = ModelMapperSpring.modelMapper().map(entrega, EntregaDTO.class);
			dtoList.add(entregaDTO);
		}
		return dtoList;
	}
	
	public List<EntregaDTO> filtros(String palabra) {
		List<EntregaDTO> dtoList = new ArrayList<>();
		Iterable<Entrega> entregas = entregaRepository.findAll(palabra);
		for(Entrega entrega: entregas) {
			EntregaDTO entregaDTO = ModelMapperSpring.modelMapper().map(entrega, EntregaDTO.class);
			dtoList.add(entregaDTO);
		}
		return dtoList;
	}
	
	@Override
	public EntregaDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Entrega> entrega = entregaRepository.findById(id);
		if(!entrega.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(entrega.get(), EntregaDTO.class);
	}
	
	@Override
	public EntregaDTO findByGuia(String guia) {
		// TODO Auto-generated method stub
		Optional<Entrega> entrega = entregaRepository.findByGuia(guia);
		if(!entrega.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(entrega.get(), EntregaDTO.class);

	}

	@Override
	public void update(EntregaRequest entregaRequest, Long id) {
		// TODO Auto-generated method stub
		Optional<Entrega> entregas = entregaRepository.findById(id);
		if(entregas.isPresent()) {
			Entrega entrega = entregas.get();
			entrega.setTipo(TipoEnvio.valueOf(entregaRequest.getTipo()));
			entrega.setProducto(entregaRequest.getProducto());
			entrega.setCantidad(entregaRequest.getCantidad());
			entrega.setFecha_entrega(entregaRequest.getFecha_entrega());
			entrega.setPrecio_envio(entregaRequest.getPrecio_envio());
			entrega.setDato_vehiculo(entregaRequest.getDato_vehiculo());
			entrega.setGuia(entregaRequest.getGuia());
			
			if(entregaRequest.getTipo().equals("TERRESTRE") && entregaRequest.getCantidad()>10) {
				entrega.realizarDescuento(new BigDecimal("5"));
			}
			
			if (entregaRequest.getTipo().equals("MARITIMO") && entregaRequest.getCantidad() > 10) {
				entrega.realizarDescuento(new BigDecimal("3"));
			}
		
			if(entregaRequest.getCantidad()<=10) {
				// si no se cumple la condicion para establecer un descuento se le asigna 0
				entrega.setDescuento(new BigDecimal("0"));
				
				// le asignamos el valor del envio al precio final
				entrega.setPrecio_final(entregaRequest.getPrecio_envio());
			}
			entregaRepository.save(entrega);
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		entregaRepository.deleteById(id);
	}

}