package com.iesmb.gestionalumnos.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotNull(message = "El año académico no puede estar vacío.")
    @Min(value = 2010, message = "El año académico no puede ser menor a 2010")
    @Max(value = 2100, message = "El año académico no puede ser mayor a 2100")
    private int anioAcademico;
	
	@NotBlank(message = "Debe ingresar un semestre de cursado.")
	@Size(max = 10, message = "El semestre no debe superar los 10 caracteres.")
    private String semestre;
	
	@NotBlank(message = "Debe ingresar un horario de cursado.")
	@Size(max = 15, message = "El horario no debe superar los 15 caracteres.")
    private String horario;
	
	@NotBlank(message = "Debe ingresar un aula de cursado.")
	@Size(max = 10, message = "El aula no debe superar los 10 caracteres.")
    private String aula;
	
	@NotNull(message = "El cupo máximo no puede estar vacío.")
    @Min(value = 1, message = "El cupo máximo no puede ser menor a 1")
    @Max(value = 500, message = "El cupo máximo no puede ser mayor a 500")
    private int cupoMaximo;
	
	@NotBlank(message = "Debe ingresar un periodo de evaluación.")
	@Size(max = 15, message = "El periodo de evaluación no debe superar los 15 caracteres.")
    private String periodoEvaluacion;

	@JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<RegistroNotas> registrosNotas;
    
	@JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<RegistroAsistencia> registrosAsistencia;

    @ManyToMany
    @JoinTable(name = "alumnosPorCurso",
    		joinColumns = {@JoinColumn(name = "id_curso")},
    		inverseJoinColumns = {@JoinColumn(name = "id_alumno")})
    private Set<Alumno> alumnos;
    
    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;
    
	@NotNull(message = "La materia no puede estar vacía.")
    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia  materia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnioAcademico() {
		return anioAcademico;
	}

	public void setAnioAcademico(int anioAcademico) {
		this.anioAcademico = anioAcademico;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public String getPeriodoEvaluacion() {
		return periodoEvaluacion;
	}

	public void setPeriodoEvaluacion(String periodoEvaluacion) {
		this.periodoEvaluacion = periodoEvaluacion;
	}

	public List<RegistroNotas> getRegistrosNotas() {
		return registrosNotas;
	}

	public void setRegistrosNotas(List<RegistroNotas> registrosNotas) {
		this.registrosNotas = registrosNotas;
	}

	public List<RegistroAsistencia> getRegistrosAsistencia() {
		return registrosAsistencia;
	}

	public void setRegistrosAsistencia(List<RegistroAsistencia> registrosAsistencia) {
		this.registrosAsistencia = registrosAsistencia;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}
