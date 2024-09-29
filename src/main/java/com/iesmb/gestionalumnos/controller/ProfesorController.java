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

import com.iesmb.gestionalumnos.entity.Profesor;
import com.iesmb.gestionalumnos.service.IProfesorService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
	
	@Autowired
	IProfesorService profesorService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Profesor>>> mostrarTodosLosProfesores() {	
		List<Profesor> profesores = profesorService.getAll();
		return (profesores.isEmpty())
				? ResponseUtil.notFound("No se encontraron profesores.")
				: ResponseUtil.success(profesores);
	}
	
	@GetMapping("/titulares")
	public ResponseEntity<APIResponse<List<Profesor>>> mostrarTodosLosTitulares(){
		List<Profesor> profesores = profesorService.encontrarProfesoresTitulares();
		return(profesores.isEmpty()) ? ResponseUtil.notFound("No se encontraron profesores titulares") : ResponseUtil.success(profesores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Profesor>> mostrarProfesorPorId(@PathVariable("id") Integer id) {
		return (profesorService.exists(id))
				? ResponseUtil.success(profesorService.getById(id))
				: ResponseUtil.notFound("No se encontró un profesor con id " + id.toString() + ".");	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Profesor>> crearProfesor(@RequestBody Profesor profesor) {
		return (profesorService.exists(profesor.getId())) 
				? ResponseUtil.badRequest("Ya existe un profesor con id " + profesor.getId().toString() + ".") 
				: ResponseUtil.created(profesorService.save(profesor), "El profesor fue creado con éxito");		
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Profesor>> modificarProfesor(@RequestBody Profesor profesor) {
		if (profesorService.exists(profesor.getId())) {
			return ResponseUtil.success(profesorService.save(profesor));
		} else if (profesor.getId()==null) {
			return ResponseUtil.badRequest("No ingresaste el id de ningún profesor para modificarlo.");
		} else {
			return ResponseUtil.notFound("No existe un profesor con id " + profesor.getId().toString() + ".");
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarProfesor(@PathVariable("id") Integer id) {
		if (profesorService.exists(id)) {
			profesorService.delete(id);
			return ResponseUtil.success("El profesor con id " + id.toString() + " ha sido eliminado.");
		} else {
			return ResponseUtil.notFound("No existe un profesor con id " + id.toString() + ".");
		}		
	}
	
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}	
	


