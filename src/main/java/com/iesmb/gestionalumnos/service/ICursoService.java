package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Curso;

public interface ICursoService {
	
	public List<Curso> getAll();
	
	public Curso getById(Long id);
	
	public Curso save(Curso registro);
	
	public void delete(Long id);
	
	public boolean exists(Long id);
}
