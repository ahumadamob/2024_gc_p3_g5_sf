package com.iesmb.gestionalumnos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class RegistroAsistencia {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Debe ingresar un alumno.")
	private Integer id_alumno;
	
	@NotNull(message = "Debe ingresar un curso.")
	private Integer id_curso;
	
	@Past(message = "La fecha y hora ingresadas no sucedieron aún.")
	private LocalDateTime fecha;
	
	@NotBlank(message = "El estado de la asistencia no puede estar vacío.")
	@Size(max = 15, message = "El estado de la asistencia no debe superar los 15 caracteres.")
	private String estadoAsistencia;
	
	@Size(max = 15, message = "El tipo de ausencia no debe superar los 15 caracteres.")
	private String tipoAusencia;
	
	@Size(max = 80, message = "Las observaciones adicionales no deben superar los 80 caracteres.")
	private String observacionesAdicionales;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Integer id_alumno) {
		this.id_alumno = id_alumno;
	}

	public Integer getId_curso() {
		return id_curso;
	}

	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getEstadoAsistencia() {
		return estadoAsistencia;
	}

	public void setEstadoAsistencia(String estadoAsistencia) {
		this.estadoAsistencia = estadoAsistencia;
	}

	public String getTipoAusencia() {
		return tipoAusencia;
	}

	public void setTipoAusencia(String tipoAusencia) {
		this.tipoAusencia = tipoAusencia;
	}

	public String getObservacionesAdicionales() {
		return observacionesAdicionales;
	}

	public void setObservacionesAdicionales(String observacionesAdicionales) {
		this.observacionesAdicionales = observacionesAdicionales;
	}
	

}
