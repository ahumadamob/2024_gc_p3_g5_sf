package com.iesmb.gestionalumnos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_notas")
public class RegistroNotas {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//AÚN NO SE PUEDEN HACER LAS RELACIONES HASTA NO TENER LAS OTRAS ENTIDADES
    @Column(name = "id_alumno")
	private Integer id_alumno;
    
    @Column(name = "id_curso")
    private Integer id_curso;

    @Column(name = "nota")
	private double nota;
    
    @Column(name = "tipo_evaluacion")
	private String tipo_evaluacion;
    
    @Column(name = "peso_porcentual")
	private String peso_porcentual;
    
    @Column(name = "observacion")
	private String observacion;
	
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
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public String getTipo_evaluacion() {
		return tipo_evaluacion;
	}
	public void setTipo_evaluacion(String tipo_evaluacion) {
		this.tipo_evaluacion = tipo_evaluacion;
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
	

	

}
