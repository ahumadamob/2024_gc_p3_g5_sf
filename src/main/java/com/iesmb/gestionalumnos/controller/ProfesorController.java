package com.iesmb.gestionalumnos.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesmb.gestionalumnos.entity.Profesor;
import com.iesmb.gestionalumnos.service.IProfesorService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
	
	@Autowired
	IProfesorService profesorService;
	
	@PutMapping("/actualizar_estado/{id}")
	public ResponseEntity<APIResponse<Profesor>> actualizarEstadoProfesor(@PathVariable("id") Integer id, @RequestBody String nuevoEstado) {

		if (!profesorService.exists(id)) {
	        return ResponseUtil.notFound("No existe un profesor con id " + id.toString() + ".");
	    }

	    List<String> estadosPermitidos = List.of("activo", "inactivo", "licenciado");
	    if (!estadosPermitidos.contains(nuevoEstado)) {
	        return ResponseUtil.badRequest("Estado inválido. Los estados permitidos son: " + estadosPermitidos);
	    }

	    return ResponseUtil.success(profesorService.updateStatus(id,nuevoEstado));
	}
	
	
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
	
    @PostMapping("/registrar_ausencia/{id}")
    public ResponseEntity<APIResponse<String>> registrarAusencia(
            @PathVariable Integer id, // Usamos @PathVariable para obtener el id del profesor
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam String tipoAusencia) {

        if (!profesorService.exists(id)) {
            return ResponseUtil.notFound("No se encontró un profesor con id " + id + ".");
        }

        boolean registrada = profesorService.registrarAusencia(id, fecha, tipoAusencia);

        return (registrada)
                ? ResponseUtil.success("La ausencia ha sido registrada exitosamente.")
                : ResponseUtil.badRequest("No se pudo registrar la ausencia. Verifique los datos proporcionados.");
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


	@PostMapping("/{profesorId}/materias/{materiaId}")
	public ResponseEntity<APIResponse<Profesor>> asignarMateria(@PathVariable Integer profesorId, @PathVariable Integer materiaId) {
		Profesor profesorActualizado = profesorService.asignarMateria(profesorId, materiaId);

		if (profesorActualizado != null) {
			return ResponseUtil.success(profesorActualizado);
		} else {
			if (!profesorService.exists(profesorId)) {
				return ResponseUtil.notFound("No se encontró un profesor con el ID " + profesorId + ".");
			} else {
				return ResponseUtil.notFound("No se encontró la materia con el ID " + materiaId + ".");
			}
		}
	}



	@DeleteMapping("/{profesorId}/materias/{materiaId}")
	public ResponseEntity<APIResponse<Profesor>> eliminarMateriaDeProfesor(
			@PathVariable("profesorId") Integer profesorId,
			@PathVariable("materiaId") Integer materiaId) {
		Profesor profesorActualizado = profesorService.eliminarMateria(profesorId, materiaId);
		if (profesorActualizado != null) {
			return ResponseUtil.success(profesorActualizado);
		} else {
			return ResponseUtil.notFound("No se encontró el profesor o la materia para eliminar la asignación.");
		}
	}








	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}	
	


