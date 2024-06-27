package com.iesmb.gestionalumnos.controller;

import com.iesmb.gestionalumnos.Entity.TablaMateria;
import com.iesmb.gestionalumnos.service.IMateriaService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiaController")
public class MateriaController {
    @Autowired
    public IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<APIResponse<List<TablaMateria>>> mostrarMaterias(){
        List<TablaMateria> materias = materiaService.getAll();
        return (materias.isEmpty()) ? ResponseUtil.notFound("No se encontraron materias en la lista.") : ResponseUtil.success(materias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TablaMateria>> mostrarMateriaPorId(@PathVariable("id") Integer id) {

        return (materiaService.exists(id)) ? ResponseUtil.success(materiaService.getById(id))
                : ResponseUtil.notFound("No se encontro una materia con el Id indicado.");
    }

    @PostMapping
    public ResponseEntity<APIResponse<TablaMateria>> crearMateria(@RequestBody TablaMateria materia) {

        return (materiaService.exists(materia.getId())) ? ResponseUtil.badRequest("Ya existe una materia con el id indicado.")
                : ResponseUtil.created(materiaService.save(materia),  "La materia fue creada con éxito.");

    }

    @PutMapping
    public ResponseEntity<APIResponse<TablaMateria>> modificarMateria(@RequestBody TablaMateria materia) {

        return (materiaService.exists(materia.getId())) ? ResponseUtil.ok(materiaService.save(materia), "La materia fue modificada con éxito.")
                : ResponseUtil.notFound("No se encontro una materia con el Id indicado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<TablaMateria>> eliminarMateria(@PathVariable("id") Integer id) {

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
