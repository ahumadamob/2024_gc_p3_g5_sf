package com.iesmb.gestionalumnos.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.RegistroNotas;
import com.iesmb.gestionalumnos.repository.RegistroNotasRepository;
import com.iesmb.gestionalumnos.service.IRegistroNotasService;

@Service
@Primary
public class RegistroNotasServiceImpl implements IRegistroNotasService {

	@Autowired
	RegistroNotasRepository repo;
	
	@Override
	public List<RegistroNotas> getAll() {
		return repo.findAll();
	}

	@Override
	public RegistroNotas getById(Integer id) {
		Optional<RegistroNotas> optional = repo.findById(id);
		return optional.orElse(null);
	}

	@Override
	public RegistroNotas save(RegistroNotas registroNotas) {
		return repo.save(registroNotas);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null)? false: repo.existsById(id);
	}
	
	@Override
	public List<RegistroNotas> findByNota(double nota) {
		return repo.findByNota(nota);
	}
	
}
