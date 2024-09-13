package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.RegistroAsistencia;

public interface IRegistroAsistenciaService {
	
	public List<RegistroAsistencia> getAll();
	public List<RegistroAsistencia> getByTipoAusencia(String tipoAusencia);
	public RegistroAsistencia getById(Integer id);
	public RegistroAsistencia save(RegistroAsistencia registro);
	public void delete(Integer id);
	public boolean exists(Integer id);

}
