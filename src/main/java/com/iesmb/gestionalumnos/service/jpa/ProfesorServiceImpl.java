package com.iesmb.gestionalumnos.service.jpa;

import java.util.List;

import com.iesmb.gestionalumnos.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.Profesor;
import com.iesmb.gestionalumnos.entity.Materia;

import com.iesmb.gestionalumnos.repository.ProfesorRepository;
import com.iesmb.gestionalumnos.service.IProfesorService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	ProfesorRepository repo;
	@Autowired
	MateriaRepository materiaRepo;

	@Override
	public List<Profesor> getAll() {		
		return repo.findAll();
	}
	
	@Override
	public Profesor getById(Integer id) {
        return repo.findById(id).orElse(null);
    }	

	@Override
	public Profesor save(Profesor profesor) {
		return repo.save(profesor);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public boolean exists(Integer id) {
	    return (id == null) ? false: repo.existsById(id);
	}
	
	@Override
	public List<Profesor> encontrarProfesoresTitulares() {
        return repo.findByTitularidad(true);
    }


	@Override
	@Transactional
	public Profesor asignarMateria(Integer profesorId, Integer materiaId) {

		Profesor profesor = repo.findById(profesorId).orElse(null);
		if (profesor == null) {
			return null;
		}
		Materia materia = materiaRepo.findById(materiaId).orElse(null);
		if (materia == null) {
			return null;
		}
		profesor.getMaterias().add(materia);
		return repo.save(profesor);
	public Profesor updateStatus(Integer id, String nuevoEstado) {
	    if (exists(id)) {
	        Profesor profesor = getById(id);
	        profesor.setEstado(nuevoEstado);
	        return save(profesor);
	    }
		return null;
	}


	@Override
	public Profesor eliminarMateria(Integer profesorId, Integer materiaId) {
			Profesor profesor = getById(profesorId);

			Materia materia = materiaRepo.findById(materiaId).orElse(null);


			if (profesor != null && materia != null) {
				profesor.getMaterias().remove(materia);
				return repo.save(profesor);
			}
			return null;
	}

}
