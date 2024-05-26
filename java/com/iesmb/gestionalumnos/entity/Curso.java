<<<<<<<< HEAD:java/com/iesmb/gestionalumnos/Entity/Curso.java
package com.iesmb.gestionalumnos.entity;

import java.util.List;
|||||||| 5635fe6:src/main/java/com/iesmb/gestionalumnos/Entity/Curso.java
package com.iesmb.gestionalumnos.Entity;
========
package com.iesmb.gestionalumnos.entity;
>>>>>>>> master:java/com/iesmb/gestionalumnos/entity/Curso.java

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;
    private String materia;
    private String profesor;
    private int anioAcademico;
    private String semestre;
    private String horario;
    private String aula;
    private int cupoMaximo;
    private String periodoEvaluacion;
<<<<<<<< HEAD:java/com/iesmb/gestionalumnos/Entity/Curso.java
    @OneToMany(mappedBy = "curso")
    private List<RegistroNotas> registroNotas;
    
|||||||| 5635fe6:src/main/java/com/iesmb/gestionalumnos/Entity/Curso.java


========
    
    @OneToOne(mappedBy = "curso")
    private Profesor profesor;

    @ManyToMany
    @JoinTable(name = "alumnosPorCurso",
    		joinColumns = {@JoinColumn(name = "id_curso")},
    		inverseJoinColumns = {@JoinColumn(name = "id_tablaAlumno")})
    private Set<TablaAlumno> tablaAlumnos;
    
    
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

>>>>>>>> master:java/com/iesmb/gestionalumnos/entity/Curso.java
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
<<<<<<<< HEAD:java/com/iesmb/gestionalumnos/Entity/Curso.java

	public List<RegistroNotas> getRegistroNotas() {
		return registroNotas;
	}

	public void setRegistroNotas(List<RegistroNotas> registroNotas) {
		this.registroNotas = registroNotas;
	}
|||||||| 5635fe6:src/main/java/com/iesmb/gestionalumnos/Entity/Curso.java
========

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
    
>>>>>>>> master:java/com/iesmb/gestionalumnos/entity/Curso.java
}
