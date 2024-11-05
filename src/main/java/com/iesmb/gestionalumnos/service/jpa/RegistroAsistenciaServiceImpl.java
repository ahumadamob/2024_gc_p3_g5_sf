package com.iesmb.gestionalumnos.service.jpa;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.controller.APIResponse;
import com.iesmb.gestionalumnos.entity.Alumno;
import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.entity.RegistroAsistencia;
import com.iesmb.gestionalumnos.repository.AlumnoRepository;
import com.iesmb.gestionalumnos.repository.CursoRepository;
import com.iesmb.gestionalumnos.repository.RegistroAsistenciaRepository;
import com.iesmb.gestionalumnos.service.IRegistroAsistenciaService;

@Service
@Primary
public class RegistroAsistenciaServiceImpl implements IRegistroAsistenciaService{

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	    
	@Autowired
	private RegistroAsistenciaRepository repo;
	
	@Override
	public List<RegistroAsistencia> getAll() {
		return repo.findAll();
	}

	@Override
	public RegistroAsistencia getById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public RegistroAsistencia save(RegistroAsistencia registro) {
		return repo.save(registro);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

	@Override
	public List<RegistroAsistencia> getByTipoAusencia(String tipoAusencia) {
		return repo.findByTipoAusencia(tipoAusencia);
	}
	
	 @Override
	    public APIResponse<RegistroAsistencia> validarYRegistrarAsistencia(Integer alumnoId, Integer cursoId, LocalDateTime fecha, String estadoAsistencia) {

	        // Validar existencia del alumno
	        Alumno alumno = alumnoRepository.findById(alumnoId).orElse(null);
	        if (alumno == null) {
	            return new APIResponse<>(HttpStatus.BAD_REQUEST.value(), Collections.singletonList("El alumno con ID " + alumnoId + " no existe."), null);
	        }

	        // Validar existencia del curso
	        Curso curso = cursoRepository.findById(cursoId).orElse(null);
	        if (curso == null) {
	            return new APIResponse<>(HttpStatus.BAD_REQUEST.value(), Collections.singletonList("El curso con ID " + cursoId + " no existe."), null);
	        }

	        // Crear y guardar el registro de asistencia
	        RegistroAsistencia asistencia = new RegistroAsistencia();
	        asistencia.setAlumno(alumno);
	        asistencia.setCurso(curso);
	        asistencia.setFecha(fecha);
	        asistencia.setEstadoAsistencia(estadoAsistencia);

	        RegistroAsistencia registroGuardado = repo.save(asistencia);

	        return new APIResponse<>(HttpStatus.CREATED.value(), Collections.singletonList("Asistencia registrada con éxito."), registroGuardado);
	    }
	

}
