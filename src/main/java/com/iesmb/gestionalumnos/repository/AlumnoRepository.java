package com.iesmb.gestionalumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Alumno;
import java.util.List;



public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
		List<Alumno> findByApellido(String apellido);

		boolean existsByIdAndActivo(Integer alumnoId, boolean b);
}
