package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Alumno;


public interface IAlumnoService {

	public List<Alumno> obtenerTodas();
	public Alumno obtenerPorId(Integer id);
	public void guardar(Alumno alumno);
	public void eliminar(Integer id);
	boolean exists(Integer id);
	
}
