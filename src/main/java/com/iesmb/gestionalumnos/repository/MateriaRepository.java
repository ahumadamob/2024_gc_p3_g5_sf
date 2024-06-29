package com.iesmb.gestionalumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iesmb.gestionalumnos.Entity.TablaMateria;

public interface MateriaRepository extends JpaRepository<TablaMateria, Integer> {
}
