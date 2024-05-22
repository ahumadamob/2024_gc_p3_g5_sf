package com.iesmb.gestionalumnos.entity;

import jakarta.persistence.*;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String materia;
    private String id_profesor;
    private int anioAcademico;
    private String semestre;
    private String horario;
    private String aula;
    private int cupoMaximo;
    private String periodoEvaluacion;
    
    
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getId_profesor() {
		return id_profesor;
	}

	public void setId_profesor(String id_profesor) {
		this.id_profesor = id_profesor;
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

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
    
    
}
