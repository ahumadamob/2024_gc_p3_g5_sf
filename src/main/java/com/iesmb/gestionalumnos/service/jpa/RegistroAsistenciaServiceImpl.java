package com.iesmb.gestionalumnos.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iesmb.gestionalumnos.entity.RegistroAsistencia;
import com.iesmb.gestionalumnos.repository.RegistroAsistenciaRepository;
import com.iesmb.gestionalumnos.service.IRegistroAsistenciaService;

@Service
@Primary
public class RegistroAsistenciaServiceImpl implements IRegistroAsistenciaService{

	@Autowired
	private RegistroAsistenciaRepository repo;
	
	@Override
	public List<RegistroAsistencia> getAll() {
		return repo.findAll();
	}

	@Override
	public RegistroAsistencia getById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public RegistroAsistencia save(RegistroAsistencia registro) {
		return repo.save(registro);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

	@Override
	public List<RegistroAsistencia> getByTipoAusencia(String tipoAusencia) {
		return repo.findByTipoAusencia(tipoAusencia);
	}

}
