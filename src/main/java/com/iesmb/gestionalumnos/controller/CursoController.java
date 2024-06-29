package com.iesmb.gestionalumnos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.service.ICursoService;


@RestController
@RequestMapping(path="/api/curso")
public class CursoController {
	@Autowired
	private ICursoService cursoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Curso>>> mostrarTodosLosRegistros() {
		List<Curso> registros = cursoService.getAll();
		return (registros.isEmpty()) ? ResponseUtil.notFound("No se encontraron cursos registrados.") 
				: ResponseUtil.success(registros);
	}


	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Curso>> mostrarRegistroPorId(@PathVariable("id") Long id_curso) {

		return (cursoService.exists(id_curso)) ? ResponseUtil.success(cursoService.getById(id_curso)) 
				: ResponseUtil.notFound("No existe un registro con el Id indicado.");
	}

	@PostMapping
	public ResponseEntity<APIResponse<Curso>> crearRegistro(@RequestBody Curso registro) {

		return (cursoService.exists(registro.getId())) ? ResponseUtil.badRequest("Ya existe un registro con este Id.")
				: ResponseUtil.created(cursoService.save(registro),  "El registro fue creado con éxito.");

	}

	@PutMapping	
	public ResponseEntity<APIResponse<Curso>> modificarCurso(@RequestBody Curso registro) {
		if (cursoService.exists(registro.getId())) {
			return ResponseUtil.success(cursoService.save(registro));
		} else if (registro.getId()==null) {
			return ResponseUtil.badRequest("No ingreso el id de ningún curso para modificarlo.");
		} else {
			return ResponseUtil.notFound("No existe un curso con id " + registro.getId().toString() + ".");
		}
	}

	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Curso>> eliminarRegistro(@PathVariable("id") Long id_curso) {

		if(cursoService.exists(id_curso)) {
			cursoService.delete(id_curso);
			return ResponseUtil.ok(null, "El registro fue eliminado.");
		}else {
			return ResponseUtil.notFound("No existe un registro con el Id indicado.");
		}
	}

}