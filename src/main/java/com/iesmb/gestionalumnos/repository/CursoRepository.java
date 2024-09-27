package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Curso;

public interface CursoRepository extends JpaRepository <Curso, Integer>{
	
	List<Curso> getBycupoMaximo(Integer cupoMaximo);

}
