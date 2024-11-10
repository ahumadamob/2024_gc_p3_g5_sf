package com.iesmb.gestionalumnos.dto;

import java.sql.Date;

public class InscripcionDTO {
	 	private Integer alumnoId;
	    private Integer cursoId;
	    private Date fechaInscripcion;

	    // Getters y setters
	    public Integer getAlumnoId() {
	        return alumnoId;
	    }

	    public void setAlumnoId(Integer alumnoId) {
	        this.alumnoId = alumnoId;
	    }

	    public Integer getCursoId() {
	        return cursoId;
	    }

	    public void setCursoId(Integer cursoId) {
	        this.cursoId = cursoId;
	    }

	    public Date getFechaInscripcion() {
	        return fechaInscripcion;
	    }

	    public void setFechaInscripcion(Date fechaInscripcion) {
	        this.fechaInscripcion = fechaInscripcion;
	    }

}
