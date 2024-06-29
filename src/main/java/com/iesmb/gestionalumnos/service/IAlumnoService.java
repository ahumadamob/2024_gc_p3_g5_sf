package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.TablaAlumno;


public interface IAlumnoService {

	public List<TablaAlumno> obtenerTodas();
	public TablaAlumno obtenerPorId(Integer id);
	public void guardar(TablaAlumno alumno);
	public void eliminar(Integer id);
	boolean exists(Integer id);
	
}
