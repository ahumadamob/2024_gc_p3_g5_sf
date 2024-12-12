package com.iesmb.gestionalumnos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

import com.iesmb.gestionalumnos.entity.RegistroAsistencia;
import com.iesmb.gestionalumnos.service.IRegistroAsistenciaService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/registroAsistencia")
public class RegistroAsistenciaController {
	
	@Autowired
	public IRegistroAsistenciaService registroAsistenciaService;
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<RegistroAsistencia>>> mostrarTodosLosRegistrosAsistencia() {
		List<RegistroAsistencia> registros = registroAsistenciaService.getAll();
		return (registros.isEmpty()) 
				? ResponseUtil.notFound("No se encontraron registros de asistencia.") 
				: ResponseUtil.success(registros);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<RegistroAsistencia>> mostrarRegistroAsistenciaPorId(@PathVariable("id") Integer id) {
		return (registroAsistenciaService.exists(id)) 
				? ResponseUtil.success(registroAsistenciaService.getById(id)) 
				: ResponseUtil.notFound("No se encontro un registro de asistencia con id " + id.toString() + ".");
	}
	
	@GetMapping("/tipoAusencia/{tipoAusencia}")
	public ResponseEntity<APIResponse<List<RegistroAsistencia>>>mostrarRegistroAsistenciaPorTipoAusencia(@PathVariable("tipoAusencia") String tipoAusencia){
		List<RegistroAsistencia> registros = registroAsistenciaService.getByTipoAusencia(tipoAusencia);
		if(registros.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron registros de asistencia con el tipo de ausencia " + tipoAusencia.toString());
		}else {
			return ResponseUtil.success(registros);
		}
	}	
	
	@PostMapping
	public ResponseEntity<APIResponse<RegistroAsistencia>> crearRegistroAsistencia(@RequestBody RegistroAsistencia registro) {
		return (registroAsistenciaService.exists(registro.getId())) 
				? ResponseUtil.badRequest("Ya existe un registro de asistencia con id " + registro.getId().toString() + ".")
				: ResponseUtil.created(registroAsistenciaService.save(registro),  "El registro fue creado con éxito.");
			
	}

	 
	 
	
	 
	 
	
	@PutMapping
	public ResponseEntity<APIResponse<RegistroAsistencia>> modificarRegistroAsistencia(@RequestBody RegistroAsistencia registro) {
		if (registroAsistenciaService.exists(registro.getId())) {
			return ResponseUtil.success(registroAsistenciaService.save(registro));
		} else if (registro.getId()==null) {
			return ResponseUtil.badRequest("No ingresaste el id de ningún registro de asistencia para modificarlo.");
		} else {
			return ResponseUtil.notFound("No existe un registro de asistencia con id " + registro.getId().toString() + ".");
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarRegistroAsistencia(@PathVariable("id") Integer id) {
		if(registroAsistenciaService.exists(id)) {
			registroAsistenciaService.delete(id);
			return ResponseUtil.success("El registro asistencia con id " + id.toString() + " ha sido eliminado con éxito.");
		}else {
			return ResponseUtil.notFound("No existe un registro asistencia con id " + id.toString() + ".");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}

}
