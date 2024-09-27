package com.iesmb.gestionalumnos.service;

import java.util.List;
import com.iesmb.gestionalumnos.entity.RegistroNotas;

public interface IRegistroNotasService {

	public List<RegistroNotas> getAll();
	public RegistroNotas getById(Integer id);
	public RegistroNotas save(RegistroNotas registroNotas);
	public void delete(Integer id);
	public boolean exists(Integer id);
	public List<RegistroNotas> findByNota(double nota);
	
}
