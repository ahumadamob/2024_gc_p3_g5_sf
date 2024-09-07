package com.iesmb.gestionalumnos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {
	
	@Autowired
	IAlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Alumno>>> mostrarTodosLosAlumnos() {		
		APIResponse<List<Alumno>> response = new APIResponse<List<Alumno>>(200, null, alumnoService.obtenerTodas());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Alumno>> mostrarAlumnoPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Alumno alumno = alumnoService.obtenerPorId(id);
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.OK.value(), null, alumno);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el alumno con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro.");
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	
	@PostMapping
	public ResponseEntity<APIResponse<Alumno>> crearAlumno(@RequestBody Alumno alumno) {
		if(alumnoService.exists(alumno.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un alumno con el id = " + alumno.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT.");
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			alumnoService.guardar(alumno);
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.CREATED.value(), null, alumno);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Alumno>> modificarAlumno(@RequestBody Alumno alumno) {
		if(this.existe(alumno.getId())) {
			alumnoService.guardar(alumno);
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.OK.value(), null, alumno);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un alumno con el ID especificado");
			messages.add("Para crear un nuevo alumno utilice el verbo POST");
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Alumno>> eliminarAlumno(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			alumnoService.eliminar(id);
			List<String> messages = new ArrayList<>();
			messages.add("El alumno que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un alumno con el id = " + id.toString());
			APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Alumno alumno = alumnoService.obtenerPorId(id);
			if(alumno == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Alumno> response = new APIResponse<Alumno>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
}
