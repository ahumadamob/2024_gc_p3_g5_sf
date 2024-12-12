package com.iesmb.gestionalumnos.entity;

public class RegistrarAusenciaRequest {
	
	
	    private String fecha;  // En formato "YYYY-MM-DD"
	    private String tipoAusencia;

	    // Getters y setters
	    public String getFecha() {
	        return fecha;
	    }

	    public void setFecha(String fecha) {
	        this.fecha = fecha;
	    }

	    public String getTipoAusencia() {
	        return tipoAusencia;
	    }

	    public void setTipoAusencia(String tipoAusencia) {
	        this.tipoAusencia = tipoAusencia;
	    }
	


}
