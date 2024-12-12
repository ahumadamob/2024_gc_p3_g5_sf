package com.iesmb.gestionalumnos.controller;

import com.iesmb.gestionalumnos.entity.Materia;
import com.iesmb.gestionalumnos.service.IMateriaService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {
    @Autowired
    public IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Materia>>> mostrarMaterias(){
        List<Materia> materias = materiaService.getAll();
        return (materias.isEmpty()) ? ResponseUtil.notFound("No se encontraron materias en la lista.") : ResponseUtil.success(materias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Materia>> mostrarMateriaPorId(@PathVariable("id") Integer id) {

        return (materiaService.exists(id)) ? ResponseUtil.success(materiaService.getById(id))
                : ResponseUtil.notFound("No se encontro una materia con el Id indicado.");
    }
    
	@GetMapping("/nivel/{nivel}")
	public ResponseEntity<APIResponse<List<Materia>>>mostrarMateriaPorNivel(@PathVariable("nivel") String nivel){
		List<Materia> materias = materiaService.getByNivel(nivel);
		if(materias.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron materias del nivel " + nivel);
		}else {
			return ResponseUtil.success(materias);
		}
	}

	@PostMapping
	public ResponseEntity<APIResponse<Materia>> crearMateria(@Valid @RequestBody Materia materia) {
		
	    // Validación del tipo de nivel
	    List<String> nivelesValidos = List.of("Básico", "Intermedio", "Avanzado");
	    if (!nivelesValidos.contains(materia.getNivel())) {
	        return ResponseUtil.badRequest("El tipo de nivel debe ser 'Básico', 'Intermedio' o 'Avanzado'.");
	    }

	    // Validación de horas semanales
	    if (materia.getHorasSemanales() < 5) {
	        return ResponseUtil.badRequest("La cantidad de horas semanales no puede ser menor a 5.");
	    }

	    // Validación de existencia de materia con el mismo nombre y nivel
	    if (materiaService.existsNombreAndNivel(materia.getNombre(), materia.getNivel())) {
	        return ResponseUtil.badRequest("Ya existe una materia con el nombre '" + materia.getNombre() + "' y nivel '" + materia.getNivel() + "'.");
	    }

	    // Creación de la materia
	    Materia createdMateria = materiaService.save(materia);
	    return ResponseUtil.created(createdMateria, "La materia fue creada con éxito.");
	}


    @PutMapping
    public ResponseEntity<APIResponse<Materia>> modificarMateria(@RequestBody Materia materia) {
        return (materiaService.exists(materia.getId())) ? ResponseUtil.ok(materiaService.save(materia), "La materia fue modificada con éxito.")
                : ResponseUtil.notFound("No se encontro una materia con el Id indicado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Materia>> eliminarMateria(@PathVariable("id") Integer id) {

        if(materiaService.exists(id)) {
            materiaService.delete(id);
            return ResponseUtil.ok(null, "La materia fue eliminada con éxito.");
        }else {
            return ResponseUtil.notFound("No se encontro una materia con el Id indicado.");
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){

        return ResponseUtil.handleConstraintException(ex);
    }



}
