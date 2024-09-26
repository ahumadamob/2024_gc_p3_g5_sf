package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.RegistroNotas;

public interface RegistroNotasRepository extends JpaRepository<RegistroNotas, Integer> {
	List<RegistroNotas> findByNota(double nota);
}
