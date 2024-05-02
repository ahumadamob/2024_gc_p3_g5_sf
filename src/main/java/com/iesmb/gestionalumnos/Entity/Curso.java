package com.iesmb.gestionalumnos.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne
    //@JoinColumn(name = "id_materia")
    private Materia materia;------------ A RELACIONAR ----------*/
    @Column(name = "materia")
    private String materia;

    /*@ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;------------ A RELACIONAR ----------*/
    @Column(name = "profesor")
    private String profesor;

    @Column(name = "anio_academico")
    private int anioAcademico;

    @Column(name = "semestre")
    private String semestre;

    @Column(name = "horario")
    private String horario;

    @Column(name = "aula")
    private String aula;

    @Column(name = "cupo_maximo")
    private int cupoMaximo;

    @Column(name = "periodo_evaluacion")
    private String periodoEvaluacion;
    

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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
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
}
