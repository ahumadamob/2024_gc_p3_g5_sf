package com.iesmb.gestionalumnos.service;
import com.iesmb.gestionalumnos.Entity.TablaMateria;


import java.util.List;

public interface IMateriaService {

    public List<TablaMateria> getAll();
    public TablaMateria getById(Integer id);
    public TablaMateria save(TablaMateria tablaMateria);
    public void delete(Integer id);
    public boolean exists(Integer id);

}
