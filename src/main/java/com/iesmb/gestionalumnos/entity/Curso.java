package com.iesmb.gestionalumnos.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;
    private String materia;
    private int anioAcademico;
    private String semestre;
    private String horario;
    private String aula;
    private int cupoMaximo;
    private String periodoEvaluacion;

    @OneToMany(mappedBy = "curso")
    private List<RegistroNotas> registroNotas;
    

    @ManyToMany
    @JoinTable(name = "alumnosPorCurso",
    		joinColumns = {@JoinColumn(name = "id_curso")},
    		inverseJoinColumns = {@JoinColumn(name = "id_tablaAlumno")})
    private Set<TablaAlumno> tablaAlumnos;
    
    
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;


    public Long getId() {
        return id_curso;
    }

    public void setId(Long id) {
        this.id_curso = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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


	public List<RegistroNotas> getRegistroNotas() {
		return registroNotas;
	}

	public void setRegistroNotas(List<RegistroNotas> registroNotas) {
		this.registroNotas = registroNotas;
	}


	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

}
