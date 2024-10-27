package com.iesmb.gestionalumnos.service.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.iesmb.gestionalumnos.entity.Alumno;
import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.repository.AlumnoRepository;
import com.iesmb.gestionalumnos.repository.CursoRepository;
import com.iesmb.gestionalumnos.service.IAlumnoService;

@Service
@Primary
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private AlumnoRepository repo;
	
    @Autowired
    private CursoRepository cursoRepo;

	@Override
	public List<Alumno> getAll() {
		return repo.findAll();
	}
	
	@Override
	public Alumno save(Alumno alumno) {
		return repo.save(alumno);
	}

	@Override
	public Alumno getById(Integer id) {
	     return repo.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);				
	}
	
	public boolean exists (Integer id) {
		return repo.existsById(id);
	}

	@Override
	public List<Alumno> findByApellido(String apellido) {
		return repo.findByApellido(apellido);
	}

	@Override
	public Map<String, Object> validarInscripcion(Integer alumnoId, Integer cursoId) {
		
	    Map<String, Object> resultado = new HashMap<>();
	    
	    Alumno alumno = repo.findById(alumnoId).orElse(null);
	    Curso curso = cursoRepo.findById(cursoId).orElse(null);

	    if (alumno == null) {
	        resultado.put("datosValidos", false);
	        resultado.put("mensaje", "El alumno con ID " + alumnoId + " no existe.");
	        return resultado;
	    }

	    if (curso == null) {
	        resultado.put("datosValidos", false);
	        resultado.put("mensaje", "El curso con ID " + cursoId + " no existe.");
	        return resultado;
	    }

	    resultado.put("datosValidos", true);
	    return resultado;
	}

}
