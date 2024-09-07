package com.iesmb.gestionalumnos.service.jpa;

import com.iesmb.gestionalumnos.entity.Materia;
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
    public List<Materia> getAll() {
        return materiaRepo.findAll();
    }

    @Override
    public Materia getById(Integer id) {
        Optional<Materia> materia = materiaRepo.findById(id);
        return materia.orElse(null);
    }

    @Override
    public Materia save(Materia tablaMateria) {
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
