package com.iesmb.gestionalumnos.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Materia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre de la materia no puede estar vacío.")
	@Size(max = 20, message = "El nombre no puede superar los 20 caracteres.")
	private String nombre;
	
	@NotBlank(message = "La descripción no puede estar vacío.")
	@Size(max = 60, message = "La descripción no puede superar los 60 caracteres.")
	private String descripcion;
	
	@NotBlank(message = "Los créditos no pueden estar vacío.")
	@Size(max = 20, message = "Los créditos no pueden superar los 20 caracteres.")
	private String creditos;
	
	@NotNull(message = "La cantidad de horas semanales no puede estar vacío.")
    @Min(value = 0, message = "La cantidad de horas semanales no puede ser menor a 0")
    @Max(value = 99, message = "La cantidad de horas semanales no puede superar los 2 dígitos")
	private int horasSemanales;
	
	@NotBlank(message = "El tipo de nivel no puede estar vacío.")
	@Size(max = 12, message = "El tipo de nivel no puede superar los 12 caracteres.")
	private String nivel;
	
	@Size(max = 50, message = "Los requisitos previos no pueden superar los 50 caracteres.")
	private String requisitosPrevios;

	@JsonIgnore
    @OneToMany(mappedBy = "materia")
    private List<Curso> cursos;
	
	
	
	
	@NotNull
	private boolean activa;
	

	
	


	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@JsonIgnore
    @ManyToMany(mappedBy = "materias")
    private List<Profesor> profesores = new ArrayList<>();
	
	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}










	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCreditos() {
		return creditos;
	}

	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}

	public int getHorasSemanales() {
		return horasSemanales;
	}

	public void setHorasSemanales(int horasSemanales) {
		this.horasSemanales = horasSemanales;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getRequisitosPrevios() {
		return requisitosPrevios;
	}

	public void setRequisitosPrevios(String requisitosPrevios) {
		this.requisitosPrevios = requisitosPrevios;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	
}
