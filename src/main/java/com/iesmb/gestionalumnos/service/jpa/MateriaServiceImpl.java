package com.iesmb.gestionalumnos.service.jpa;

import com.iesmb.gestionalumnos.entity.Materia;
import com.iesmb.gestionalumnos.repository.MateriaRepository;
import com.iesmb.gestionalumnos.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
public class MateriaServiceImpl implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepo;


    @Override
    public List<Materia> getAll() {
        return materiaRepo.findAll();
    }

    @Override
    public Materia getById(Integer id) {
    	return materiaRepo.findById(id).orElse(null);
    }

    @Override
    public Materia save(Materia materia) {
        return materiaRepo.save(materia);
    }

    @Override
    public void delete(Integer id) {
        materiaRepo.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return (id == null)? false: materiaRepo.existsById(id);
    }

	@Override
	public List<Materia> getByNivel(String nivel) {
		return materiaRepo.findByNivel(nivel);
	}

    @Override
    public boolean existsNombreAndNivel(String nombre, String nivel) {
        return materiaRepo.existsByNombreAndNivel(nombre, nivel);
    }
}
