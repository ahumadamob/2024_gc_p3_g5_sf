package com.iesmb.gestionalumnos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iesmb.gestionalumnos.entity.Curso;
import com.iesmb.gestionalumnos.service.ICursoService;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping(path="/api/curso")
public class CursoController {
	@Autowired
	private ICursoService cursoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Curso>>> mostrarTodosLosCursos() {	
		List<Curso> cursos = cursoService.getAll();
		return (cursos.isEmpty())
				? ResponseUtil.notFound("No se encontraron cursos.")
				: ResponseUtil.success(cursos);
	}


	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Curso>> mostrarCursoPorId(@PathVariable("id") Integer id) {
		return (cursoService.exists(id))
				? ResponseUtil.success(cursoService.getById(id))
				: ResponseUtil.notFound("No se encontró un curso con id " + id.toString() + ".");	
	}
	
	@GetMapping("/cupoMaximo/{cupoMaximo}")
	public ResponseEntity<APIResponse<List<Curso>>>mostrarCursoPorCantidad(@PathVariable("cupoMaximo") Integer cupoMaximo){
		List<Curso> cursos = cursoService.getBycupoMaximo(cupoMaximo);
		if(cursos.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron cursos con el cupo " + cupoMaximo.toString());
		}else {
			return ResponseUtil.success(cursos);
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Curso>> crearCurso(@RequestBody Curso curso) {
		return (cursoService.exists(curso.getId())) 
				? ResponseUtil.badRequest("Ya existe un curso con id " + curso.getId().toString() + ".") 
				: ResponseUtil.created(cursoService.save(curso), "El curso fue creado con éxito");		
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Curso>> modificarCurso(@RequestBody Curso curso) {
		if (cursoService.exists(curso.getId())) {
			return ResponseUtil.success(cursoService.save(curso));
		} else if (curso.getId()==null) {
			return ResponseUtil.badRequest("No ingresaste el id de ningún curso para modificarlo.");
		} else {
			return ResponseUtil.notFound("No existe un curso con id " + curso.getId().toString() + ".");
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarCurso(@PathVariable("id") Integer id) {
		if (cursoService.exists(id)) {
			cursoService.delete(id);
			return ResponseUtil.success("El curso con id " + id.toString() + " ha sido eliminado.");
		} else {
			return ResponseUtil.notFound("No existe un curso con id " + id.toString() + ".");
		}		
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}	
	