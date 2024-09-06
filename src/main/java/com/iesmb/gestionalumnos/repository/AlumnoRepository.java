package com.iesmb.gestionalumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.Alumno;


public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}
