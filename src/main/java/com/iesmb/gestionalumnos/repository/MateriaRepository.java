package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
	
	public List<Materia> findByNivel(String nivel);
}
