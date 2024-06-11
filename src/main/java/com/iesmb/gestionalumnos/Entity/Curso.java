package com.iesmb.gestionalumnos.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.iesmb.gestionalumnos.entity.RegistroAsistencia;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;
    private Long id_profesor;
    private String materia;
    private String profesor;
    private int anioAcademico;
    private String semestre;
    private String horario;
    private String aula;
    private int cupoMaximo;
    private String periodoEvaluacion;

    @OneToMany(mappedBy = "curso")
    private List<RegistroAsistencia> registroAsistencia;

    public Long getId_curso() {return id_curso;}
    public void setId_curso(Long id_curso) {this.id_curso = id_curso;}
    public Long getId_profesor() {return id_profesor;}
    public void setId_profesor(Long id_profesor) {this.id_profesor = id_profesor;}
    public String getMateria() {return materia;}
    public void setMateria(String materia) {this.materia = materia;}
    public String getProfesor() {return profesor;}
    public void setProfesor(String profesor) {this.profesor = profesor;}
    public int getAnioAcademico() {return anioAcademico;}
    public void setAnioAcademico(int anioAcademico) {this.anioAcademico = anioAcademico;}
    public String getSemestre() {return semestre;}
    public void setSemestre(String semestre) {this.semestre = semestre;}
    public String getHorario() {return horario;}
    public void setHorario(String horario) {this.horario = horario;}
    public String getAula() {return aula;}
    public void setAula(String aula) {this.aula = aula;}
    public int getCupoMaximo() {return cupoMaximo;}
    public void setCupoMaximo(int cupoMaximo) {this.cupoMaximo = cupoMaximo;}
    public String getPeriodoEvaluacion() {return periodoEvaluacion;}
    public void setPeriodoEvaluacion(String periodoEvaluacion) {this.periodoEvaluacion = periodoEvaluacion;}

    public List<RegistroAsistencia> getRegistroAsistencia() {return registroAsistencia;}
    public void setRegistroAsistencia(List<RegistroAsistencia> registroAsistencia) {this.registroAsistencia = registroAsistencia;}

}
