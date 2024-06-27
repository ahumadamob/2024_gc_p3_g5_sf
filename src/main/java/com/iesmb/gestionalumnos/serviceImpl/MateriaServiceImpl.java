package com.iesmb.gestionalumnos.serviceImpl;

import com.iesmb.gestionalumnos.Entity.TablaMateria;
import com.iesmb.gestionalumnos.repository.MateriaRepository;
import com.iesmb.gestionalumnos.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepo;


    @Override
    public List<TablaMateria> getAll() {
        return materiaRepo.findAll();
    }

    @Override
    public TablaMateria getById(Integer id) {
        Optional<TablaMateria> materia = materiaRepo.findById(id);
        return materia.orElse(null);
    }

    @Override
    public TablaMateria save(TablaMateria tablaMateria) {
        return materiaRepo.save(tablaMateria);
    }

    @Override
    public void delete(Integer id) {
        materiaRepo.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return (id == null)? false: materiaRepo.existsById(id);
    }
}
