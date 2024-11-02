package com.iesmb.gestionalumnos.service.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.Profesor;
import com.iesmb.gestionalumnos.repository.ProfesorRepository;
import com.iesmb.gestionalumnos.service.IProfesorService;

@Service
@Primary
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	ProfesorRepository repo;


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
    public boolean registrarAusencia(Integer id, LocalDate fecha, String tipoAusencia) {

        if (!repo.existsById(id)) {
            return false;
        }        if (fecha == null || tipoAusencia == null || tipoAusencia.trim().isEmpty()) {
        			return false;
        		 }

        Profesor profesor = repo.findById(id).orElse(null);
        if (profesor != null) {

            profesor.agregarAusencia(fecha, tipoAusencia);
            
            repo.save(profesor);
            return true;
        }

        return false;
    }
	
}