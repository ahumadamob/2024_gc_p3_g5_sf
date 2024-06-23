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

import com.iesmb.gestionalumnos.entity.RegistroAsistencia;
import com.iesmb.gestionalumnos.service.IRegistroAsistenciaService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/registroAsistencia")
public class RegistroAsistenciaController {
	
	@Autowired
	public IRegistroAsistenciaService registroAsistenciaService;
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<RegistroAsistencia>>> mostrarTodosLosRegistros() {
		List<RegistroAsistencia> registros = registroAsistenciaService.getAll();
		return (registros.isEmpty()) ? ResponseUtil.notFound("No se encontraron registros.") 
				: ResponseUtil.success(registros);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<RegistroAsistencia>> mostrarRegistroPorId(@PathVariable("id") Integer id) {
		
		return (registroAsistenciaService.exists(id)) ? ResponseUtil.success(registroAsistenciaService.getById(id)) 
				: ResponseUtil.notFound("No se encontro un registro con el Id especificado.");
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<RegistroAsistencia>> crearRegistro(@RequestBody RegistroAsistencia registro) {
		
		return (registroAsistenciaService.exists(registro.getId())) ? ResponseUtil.badRequest("Ya existe un registro con este Id.")
				: ResponseUtil.created(registroAsistenciaService.save(registro),  "El registro fue creado con éxito.");
			
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<RegistroAsistencia>> modificarRegistro(@RequestBody RegistroAsistencia registro) {
		
		return (registroAsistenciaService.exists(registro.getId())) ? ResponseUtil.ok(registroAsistenciaService.save(registro), "El registro fue modificado con éxito.") 
				: ResponseUtil.notFound("No se encontro un registro con el Id especificado.");
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<RegistroAsistencia>> eliminarRegistro(@PathVariable("id") Integer id) {
		
		if(registroAsistenciaService.exists(id)) {
			registroAsistenciaService.delete(id);
			return ResponseUtil.ok(null, "El registro fue eliminado con éxito.");
		}else {
			return ResponseUtil.notFound("No se encontro un registro con el Id especificado.");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
	
		return ResponseUtil.handleConstraintException(ex);
	}

}
