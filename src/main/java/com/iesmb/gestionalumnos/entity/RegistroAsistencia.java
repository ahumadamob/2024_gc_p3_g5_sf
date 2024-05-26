package com.iesmb.gestionalumnos.entity;

import java.time.LocalDateTime;

import com.iesmb.gestionalumnos.Entity.Curso;
import jakarta.persistence.*;
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
	
	@Past(message = "La fecha y hora ingresadas no sucedieron aún.")
	private LocalDateTime fecha;
	
	@NotBlank(message = "El estado de la asistencia no puede estar vacío.")
	@Size(max = 15, message = "El estado de la asistencia no debe superar los 15 caracteres.")
	private String estadoAsistencia;
	
	@Size(max = 15, message = "El tipo de ausencia no debe superar los 15 caracteres.")
	private String tipoAusencia;
	
	@Size(max = 80, message = "Las observaciones adicionales no deben superar los 80 caracteres.")
	private String observacionesAdicionales;

	@ManyToOne
	@JoinColumn(name = "curso_reg_asist")
	private Curso curso;



	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

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
