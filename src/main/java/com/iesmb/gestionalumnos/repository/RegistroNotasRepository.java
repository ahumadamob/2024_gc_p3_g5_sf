package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Alumno;
import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.entity.RegistroNotas;

public interface RegistroNotasRepository extends JpaRepository<RegistroNotas, Integer> {
	List<RegistroNotas> findByNota(double nota);
	//Definición de nuevos métodos 
	boolean existsByAlumnoAndCursoAndTipoEvaluacion(Alumno alumno, Curso curso, String tipoEvaluacion);
	List<RegistroNotas> findByCursoAndNotaGreaterThanEqual(Curso curso, Double nota);
}