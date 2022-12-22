package com.ingeneo.pruebaspringbootbackend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoDTO;
import com.ingeneo.pruebaspringbootbackend.dto.AlmacenamientoRequest;
import com.ingeneo.pruebaspringbootbackend.entities.Almacenamiento;
import com.ingeneo.pruebaspringbootbackend.enums.TipoAlmacenamiento;
import com.ingeneo.pruebaspringbootbackend.repository.AlmacenamientoRepository;
import com.ingeneo.pruebaspringbootbackend.services.IAlmacenamientoService;
import com.ingeneo.pruebaspringbootbackend.utils.configurations.ModelMapperSpring;


@Service
public class AlmacenamientoServiceImpl implements IAlmacenamientoService{

	@Autowired
	private AlmacenamientoRepository almacenamientoRepository;
	
	@Override
	public void save(AlmacenamientoRequest almacenamientoRequest ) {
		// TODO Auto-generated method stub
		Almacenamiento almacenamiento = ModelMapperSpring.modelMapper().map(almacenamientoRequest, Almacenamiento.class);
		almacenamientoRepository.save(almacenamiento);
	}

	@Override
	public List<AlmacenamientoDTO> findAll() {
		// TODO Auto-generated method stub
		List<AlmacenamientoDTO> dtoList = new ArrayList<>();
		Iterable<Almacenamiento> almacenamientos = almacenamientoRepository.findAll();
		for(Almacenamiento almacenamiento : almacenamientos) {
			AlmacenamientoDTO almacenamientoDTO = ModelMapperSpring.modelMapper().map(almacenamiento, AlmacenamientoDTO.class);
			dtoList.add(almacenamientoDTO);
		}
		return dtoList;
	}
	
	@Override
	public List<AlmacenamientoDTO> findByTipo(TipoAlmacenamiento tipo) {
		// TODO Auto-generated method stub
		List<AlmacenamientoDTO> dtoList = new ArrayList<>();
		Iterable<Almacenamiento> almacenamientos = almacenamientoRepository.findByTipo(tipo);
		for(Almacenamiento almacenamiento : almacenamientos) {
			AlmacenamientoDTO almacenamientoDTO = ModelMapperSpring.modelMapper().map(almacenamiento, AlmacenamientoDTO.class);
			dtoList.add(almacenamientoDTO);
		}
		return dtoList;
	}

	@Override
	public AlmacenamientoDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Almacenamiento> almacenamiento = almacenamientoRepository.findById(id);
		if(!almacenamiento.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(almacenamiento.get(), AlmacenamientoDTO.class);
	}

	@Override
	public AlmacenamientoDTO findByNombre(String nombre) {
		// TODO Auto-generated method stub
		Optional<Almacenamiento> almacenamiento = almacenamientoRepository.findByNombre(nombre);
		if(!almacenamiento.isPresent()) {
			return null;
		}
		return ModelMapperSpring.modelMapper().map(almacenamiento.get(), AlmacenamientoDTO.class);
	}

	@Override
	public void udpdate(AlmacenamientoRequest almacenamientoRequest, Long id) {
		// TODO Auto-generated method stub
		Optional<Almacenamiento> almacenamientos = almacenamientoRepository.findById(id);
		if(almacenamientos.isPresent()) {
			Almacenamiento almacenamiento = almacenamientos.get();
			almacenamiento.setTipo(TipoAlmacenamiento.valueOf(almacenamientoRequest.getTipo()));
			almacenamiento.setNombre(almacenamientoRequest.getNombre());
			almacenamientoRepository.save(almacenamiento);
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		almacenamientoRepository.deleteById(id);
	}

}
