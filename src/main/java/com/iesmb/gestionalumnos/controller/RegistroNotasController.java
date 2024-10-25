package com.iesmb.gestionalumnos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesmb.gestionalumnos.entity.RegistroNotas;
import com.iesmb.gestionalumnos.service.IRegistroNotasService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/registro_notas")
public class RegistroNotasController {

	@Autowired
	public IRegistroNotasService registroNotasService;

	@GetMapping
	public ResponseEntity<APIResponse<List<RegistroNotas>>> mostrarTodosLosRegistroNotas() {
		List<RegistroNotas> registroNotas = registroNotasService.getAll();
		return(registroNotas.isEmpty()) ? ResponseUtil.notFound("No se encontraron los registros de las notas.")
						: ResponseUtil.success(registroNotas);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<RegistroNotas>> mostrarRegistroNotasPorId(@PathVariable("id") Integer id) {
		return (registroNotasService.exists(id)) ? ResponseUtil.success(registroNotasService.getById(id))
						: ResponseUtil.notFound("No se encontraron registros de notas con este id.");
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<RegistroNotas>> crearRegistroNotas(@RequestBody RegistroNotas registroNotas) {
		return (registroNotasService.exists(registroNotas.getId()))
						? ResponseUtil.badRequest("Ya existe un registro de notas con este id.")
						: ResponseUtil.created(registroNotasService.save(registroNotas), "El registro fue creado correctamente.");
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<RegistroNotas>> modificarRegistroNotas(@RequestBody RegistroNotas registroNotas) {
		return (registroNotasService.exists(registroNotas.getId()))
						? ResponseUtil.ok(registroNotasService.save(registroNotas), "Se modificó correctamente el registro de notas.")
						: ResponseUtil.notFound("No se encontraron registros de notas con este id.");
	}
	
	@PatchMapping("/actualizar_nota/{id}")
	public ResponseEntity<APIResponse<RegistroNotas>> actualizarNota(
	        @PathVariable("id") Integer id, 
	        @RequestBody RegistroNotas nuevaNota) {
	    if (registroNotasService.exists(id)) {
	        RegistroNotas registro = registroNotasService.getById(id);
	        if (registro.getNota() != null && registro.getNota().equals(nuevaNota.getNota())) {
	            return ResponseUtil.badRequest("La nota ya posee dicho valor: " + nuevaNota.getNota());
	        }
	        registro.setNota(nuevaNota.getNota());
	        RegistroNotas updatedRegistro = registroNotasService.save(registro);
	        return ResponseUtil.ok(updatedRegistro, "La nota fue actualizada correctamente.");
	    } else {
	        return ResponseUtil.notFound("No se encontraron registros de notas con este id.");
	    }
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<String>> eliminarRegistroNotas(@PathVariable("id") Integer id) {
		if (registroNotasService.exists(id)) {
			registroNotasService.delete(id);
				return ResponseUtil.success("Se eliminó correctamente el registro de notas.");
			} else {
				return ResponseUtil.notFound("No se encontraron registros de notas con este id.");
		}
	}
	
	@GetMapping("/by_nota")
	public ResponseEntity<APIResponse<List<RegistroNotas>>> buscarPorNota(@RequestParam double nota) {
		List<RegistroNotas> registros = registroNotasService.findByNota(nota);
		return (registros.isEmpty()) ? ResponseUtil.notFound("No se encontraron registros con la nota especificada")
					: ResponseUtil.success(registros);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
	
}
