package com.iesmb.gestionalumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesmb.gestionalumnos.entity.TablaAlumno;


public interface AlumnoRepository extends JpaRepository<TablaAlumno, Integer> {

}
