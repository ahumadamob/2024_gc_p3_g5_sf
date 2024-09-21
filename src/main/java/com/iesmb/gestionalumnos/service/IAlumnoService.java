package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Alumno;


public interface IAlumnoService {

	public List<Alumno> getAll();
	public Alumno getById(Integer id);
	public Alumno save(Alumno alumno);
	public void delete(Integer id);
	public boolean exists(Integer id);	
}
