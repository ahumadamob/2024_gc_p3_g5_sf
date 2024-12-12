package com.iesmb.gestionalumnos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.iesmb.gestionalumnos.controller.APIResponse;
import com.iesmb.gestionalumnos.entity.RegistroAsistencia;

public interface IRegistroAsistenciaService {
	
	public List<RegistroAsistencia> getAll();
	public List<RegistroAsistencia> getByTipoAusencia(String tipoAusencia);
	public RegistroAsistencia getById(Integer id);
	public RegistroAsistencia save(RegistroAsistencia registro);
	public void delete(Integer id);
	public boolean exists(Integer id);
	public APIResponse<RegistroAsistencia> validarYRegistrarAsistencia(Integer alumnoId, Integer cursoId,
			LocalDateTime fecha, String estadoAsistencia);
	
	APIResponse<RegistroAsistencia> registrarAsistenciaValidada(Integer alumnoId, Integer cursoId, LocalDateTime fecha, String estadoAsistencia);


}
