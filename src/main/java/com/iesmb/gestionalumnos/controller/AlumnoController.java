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

import com.iesmb.gestionalumnos.entity.Alumno;
import com.iesmb.gestionalumnos.service.IAlumnoService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {
	
	@Autowired
	public IAlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Alumno>>> mostrarTodosLosAlumnos() {		
		List<Alumno> alumno = alumnoService.getAll();
		return (alumno.isEmpty())
				? ResponseUtil.notFound("No se encontraron alumnos.")
				: ResponseUtil.success(alumno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Alumno>> mostrarAlumnoPorId(@PathVariable("id") Integer id) {
		return (alumnoService.exists(id))
				? ResponseUtil.success(alumnoService.getById(id))
				: ResponseUtil.notFound("No se encontró ningún alumno.");
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Alumno>> crearAlumno(@RequestBody Alumno alumno) {
		return (alumnoService.exists(alumno.getId()))
				? ResponseUtil.badRequest("Ya existe un alumno con el id " + alumno.getId().toString() + ".")
				: ResponseUtil.created(alumnoService.save(alumno), "El alumno fue creado correctamente.");
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Alumno>> modificarAlumno(@RequestBody Alumno alumno) {
		if(alumnoService.exists(alumno.getId())) {
			return ResponseUtil.success(alumnoService.save(alumno));
		} else if (alumno.getId() == null) {
			return ResponseUtil.badRequest("No se ingresó el id de ningún alumno registrado.");
		} else {
			return ResponseUtil.notFound("No existe ningún alumno con el id " + alumno.getId().toString() + ".");
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarAlumno(@PathVariable("id") Integer id) {
		if(alumnoService.exists(id)) {
			alumnoService.delete(id);
			return ResponseUtil.success("Se eliminó correctamente el alumno.");
		} else {
			return ResponseUtil.notFound("No existe un alumno con el id " + id.toString() + ".");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
			return ResponseUtil.handleConstraintException(ex);
	}
}
