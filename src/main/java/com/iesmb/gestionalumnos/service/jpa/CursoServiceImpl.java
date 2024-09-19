package com.iesmb.gestionalumnos.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.repository.CursoRepository;
import com.iesmb.gestionalumnos.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {
	
	@Autowired
	private CursoRepository repo;

	@Override
	public List<Curso> getAll() {
		return repo.findAll();
	}

	@Override
	public Curso getById(Integer id) {
		return repo.findById(id).orElseGet(null);
	}

	@Override
	public Curso save(Curso curso) {
		return repo.save(curso);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public boolean exists(Integer id) {
		return id == null ? false : repo.existsById(id);
	}


}
