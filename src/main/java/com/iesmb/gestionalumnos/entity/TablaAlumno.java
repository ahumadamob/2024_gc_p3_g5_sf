package com.iesmb.gestionalumnos.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class TablaAlumno {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max = 30, message = "El nombre no debe superar los 30 caracteres.")
	private String nombre;
	@NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max = 30, message = "El apellido no debe superar los 30 caracteres.")
	private String apellido;
	@NotNull(message = "La fecha de nacimiento no pueden estar vacía.")
	@Future(message = "La fecha de nacimiento es incorrecta.")
	private Date fechaNacimiento;
	@NotBlank(message = "Debe indicar un correo electrónico.")
	private String correoElectronico;
    @NotBlank(message = "La dirección no puede estar vacía.")
	@Size(max = 60, message = "La dirección no debe superar los 60 caracteres.")
    private String direccion;
    @NotBlank(message = "Debe ingresar un número de teléfono.")
	@Size(max = 10, message = "El teléfono no debe superar los 10 caracteres.")
    private String numeroTelefono;
    private String genero;
    @NotNull(message = "La fecha de inscripción no pueden estar vacía.")
	@Future(message = "La fecha de inscripción es incorrecta.")
    private Date fechaInscripcion;
    private String estado;
    
    @ManyToMany(mappedBy = "tablaAlumnos")
    private Set<Curso> cursos;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
}
