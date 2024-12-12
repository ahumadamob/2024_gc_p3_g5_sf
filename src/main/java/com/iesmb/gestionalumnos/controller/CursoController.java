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
import com.iesmb.gestionalumnos.entity.Profesor;
import com.iesmb.gestionalumnos.service.ICursoService;
import com.iesmb.gestionalumnos.service.IProfesorService;

import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping(path="/api/curso")
public class CursoController {
	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	IProfesorService profesorService;
	
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
	
	
	@PostMapping("/crearAula")
	public ResponseEntity<APIResponse<Curso>> crearCursoCon50(@RequestBody Curso curso) {
		if (curso.getCupoMaximo() > 50) {
			return ResponseUtil.badRequest("El cupo máximo no puede ser mayor a 50.");
		}
		Profesor profesor = profesorService.getById(curso.getProfesor().getId());
		if (profesor == null || profesor.getExperienciaAnios() <= 5) {
			return ResponseUtil.badRequest("El profesor debe tener más de 5 años de experiencia.");
		}
		if (cursoService.existsByAulaAndSemestre(curso.getAula(), curso.getSemestre())) {
			return ResponseUtil.badRequest("Ya existe un curso con el nombre '" + curso.getAula() + "' en el semestre '" + curso.getSemestre() + "'.");
		}
		curso.setProfesor(profesor);
		Curso nuevoCurso = cursoService.save(curso);
		return ResponseUtil.created(nuevoCurso, "El curso fue creado con éxito");
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
	