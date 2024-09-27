package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.RegistroAsistencia;

public interface RegistroAsistenciaRepository extends JpaRepository<RegistroAsistencia, Integer> {

	public List<RegistroAsistencia> findByTipoAusencia(String tipoAusencia);
	
	
}
