package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Curso;

public interface ICursoService {
	
	public List<Curso> getAll();
	
	public Curso getById(Integer id);
	
	public Curso save(Curso curso);
	
	public void delete(Integer id);
	
	public boolean exists(Integer id);
}
