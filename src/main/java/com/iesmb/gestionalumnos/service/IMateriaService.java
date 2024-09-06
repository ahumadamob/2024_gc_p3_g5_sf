package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Materia;

public interface IMateriaService {

    public List<Materia> getAll();
    public Materia getById(Integer id);
    public Materia save(Materia tablaMateria);
    public void delete(Integer id);
    public boolean exists(Integer id);

}
