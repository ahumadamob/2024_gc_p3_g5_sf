package com.iesmb.gestionalumnos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class TablaMateria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre de la materia no puede estar vacío.")
	@Size(max = 40, message = "El nombre no puede superar los 40 caracteres.")
	private String nombreMateria;
	
	@NotBlank(message = "El nombre de la descripción no puede estar vacío.")
	private String descripcion;
	
	@NotBlank(message = "El nombre de los créditos no puede estar vacío.")
	private String creditos;
	
	@NotBlank(message = "La cantidad de horas semanales no puede estar vacío.")
	@Size(max = 2, message = "La cantidad de horas semanales no puede superar los 2 dígitos.")
	private int horasSemanales;
	
	@NotBlank(message = "El tipo de nivel no puede estar vacío.")
	@Size(max = 12, message = "El tipo de nivel no puede superar los 12 caracteres.")
	private String Nivel;
	
	@NotBlank(message = "El nombre de los requisitos previos no puede estar vacío.")
	private String requisitosPrevios;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
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
		return Nivel;
	}

	public void setNivel(String nivel) {
		Nivel = nivel;
	}

	public String getRequisitosPrevios() {
		return requisitosPrevios;
	}

	public void setRequisitosPrevios(String requisitosPrevios) {
		this.requisitosPrevios = requisitosPrevios;
	}
	
}
