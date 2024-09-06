package com.iesmb.gestionalumnos.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.Alumno;
import com.iesmb.gestionalumnos.repository.AlumnoRepository;
import com.iesmb.gestionalumnos.service.IAlumnoService;

@Service
@Primary
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	AlumnoRepository repo;

	@Override
	public List<Alumno> obtenerTodas() {
		
		return repo.findAll();
	}
	
	@Override
	public void guardar(Alumno alumno) {
		repo.save(alumno);
		
	}

	 @Override
	    public Alumno obtenerPorId(Integer id) {
	        Optional<Alumno> optional = repo.findById(id);
	        return optional.orElse(null);
	    }

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);				
	}
	
	public boolean exists (Integer id) {
		return repo.existsById(id);
	}

}
