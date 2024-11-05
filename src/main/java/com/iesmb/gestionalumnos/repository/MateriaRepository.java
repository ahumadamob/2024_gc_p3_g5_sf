package com.iesmb.gestionalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Materia;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {


	List<Materia> findByNivel(String nivel);
}
