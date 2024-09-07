package com.iesmb.gestionalumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
}
