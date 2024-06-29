package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Profesor;

public interface IProfesorService {

	public List<Profesor> getAll ();
	public Profesor getById (Integer id);
	public Profesor save (Profesor profesor);
	public void delete (Integer id);
	public boolean exists (Integer id);
	
}