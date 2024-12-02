package com.iesmb.gestionalumnos.service.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.entity.RegistroNotas;
import com.iesmb.gestionalumnos.repository.RegistroNotasRepository;
import com.iesmb.gestionalumnos.service.IRegistroNotasService;

@Service
@Primary
public class RegistroNotasServiceImpl implements IRegistroNotasService {

	@Autowired
	RegistroNotasRepository repo;
	
	@Override
	public List<RegistroNotas> getAll() {
		return repo.findAll();
	}

	@Override
	public RegistroNotas getById(Integer id) {
		Optional<RegistroNotas> optional = repo.findById(id);
		return optional.orElse(null);
	}

	@Override
	public RegistroNotas save(RegistroNotas registroNotas) {
		//Para verificar si ya existe una nota con el mismo alumno, curso y tipo de evaluacion 
		if (repo.existsByAlumnoAndCursoAndTipoEvaluacion(
				registroNotas.getAlumno(),
				registroNotas.getCurso(),
				registroNotas.getTipoEvaluacion())) {
		throw new IllegalArgumentException("Ya existe una nota para este alumno en este curso con este tipo de evaluación.");
	}
	
	//Para verificar si "esFinal" coincide con el tipo de evaluacion  (sólo puede ser true si el tipo es "final").

	if (registroNotas.getEsFinal() !=null && registroNotas.getEsFinal()&&
		!registroNotas.getTipoEvaluacion().equalsIgnoreCase("final")) {
		throw new IllegalArgumentException("El campo 'esFinal' sólo puede ser verdadero si el tipo de evaluación es 'final'.");
	}
	
	//Establecer la fecha de registro automático al momento de guardar
	
	registroNotas.setFechaRegistro(LocalDate.now().toString());
	return repo.save(registroNotas);
}
	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null)? false: repo.existsById(id);
	}
	
	@Override
	public List<RegistroNotas> findByNota(double nota) {
		return repo.findByNota(nota);
	}
	
	//Obtención de cierta nota en un determinado curso
	
	public List<RegistroNotas> findByCursoAndNotasGreaterThanEqual(Curso curso, Double nota) {
		return repo.findByCursoAndNotaGreaterThanEqual(curso, nota);
	}
	
}
