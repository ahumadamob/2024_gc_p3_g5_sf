package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer>{
	
    List<Profesor> findByTitularidad(boolean titularidad);

}