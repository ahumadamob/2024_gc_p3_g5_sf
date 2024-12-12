package com.iesmb.gestionalumnos.service;

import java.util.List;

import com.iesmb.gestionalumnos.entity.Materia;

public interface IMateriaService {

    public List<Materia> getAll();
    public List<Materia> getByNivel(String nivel);
    public Materia getById(Integer id);
    public Materia save(Materia materia);
    public void delete(Integer id);
    public boolean exists(Integer id);
    public boolean existsNombreAndNivel(String nombre, String nivel);

}
