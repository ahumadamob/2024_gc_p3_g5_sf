package com.iesmb.gestionalumnos.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.Profesor;
import com.iesmb.gestionalumnos.repository.ProfesorRepository;
import com.iesmb.gestionalumnos.service.IProfesorService;

@Service
@Primary
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	ProfesorRepository repo;

	@Override
	public List<Profesor> getAll() {		
		return repo.findAll();
	}
	
	@Override
	public Profesor getById(Integer id) {
        return repo.findById(id).orElse(null);
    }	

	@Override
	public Profesor save(Profesor profesor) {
		return repo.save(profesor);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public boolean exists(Integer id) {
	    return (id == null) ? false: repo.existsById(id);
	}
	
	@Override
	public List<Profesor> encontrarProfesoresTitulares() {
        return repo.findByTitularidad(true);
    }

	@Override
	public Profesor updateStatus(Integer id, String nuevoEstado) {
	    if (exists(id)) {
	        Profesor profesor = getById(id);
	        profesor.setEstado(nuevoEstado);
	        return save(profesor);
	    }
		return null;
	}
	
}