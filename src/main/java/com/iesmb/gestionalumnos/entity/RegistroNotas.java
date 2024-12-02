package com.iesmb.gestionalumnos.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "registo_notas")
public class RegistroNotas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Min(value = 1, message = "La nota no puede ser menor a 1")
    @Max(value = 10, message = "La nota no puede ser mayor a 10")
	private Double nota;
    
    @NotBlank(message = "El tipo de evaluación no puede estar vacío.")
	@Size(max = 15, message = "El tipo de evaluación no debe superar los 15 caracteres.")
	private String tipoEvaluacion;
    
    @NotBlank(message = "El peso porcentual no puede estar vacío.")
	@Size(max = 15, message = "El peso porcentual no debe superar los 15 caracteres.")
	private String peso_porcentual;
    
	@Size(max = 70, message = "La observación no debe superar los 70 caracteres.")
	private String observacion;
	
	@NotNull(message = "Se debe ingresar un curso.")
	@ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
	private Curso curso;

	@NotNull(message = "Se debe ingresar un alumno.")
	@ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
	private Alumno alumno;
	
	//Agrego atributos "fechaRegisto" y "esFinal"
	//Creacion de getter y setter
	private String fechaRegistro;
	
	private Boolean esFinal;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}

	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}

	public String getPeso_porcentual() {
		return peso_porcentual;
	}

	public void setPeso_porcentual(String peso_porcentual) {
		this.peso_porcentual = peso_porcentual;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String string) {
		this.fechaRegistro = string;
	}

	public Boolean getEsFinal() {
		return esFinal;
	}

	public void setEsFinal(Boolean esFinal) {
		this.esFinal = esFinal;
	}
	

}
