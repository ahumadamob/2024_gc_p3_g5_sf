package com.iesmb.gestionalumnos.entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Alumno {

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
	@Past
	private Date fechaNacimiento;
	
	@NotBlank(message = "Debe indicar un correo electrónico.")
	@Size(max = 40, message = "El correo no debe superar los 40 caracteres.")
	private String correoElectronico;
	
    @NotBlank(message = "La dirección no puede estar vacía.")
	@Size(max = 60, message = "La dirección no debe superar los 60 caracteres.")
    private String direccion;
    
    @NotBlank(message = "Debe ingresar un número de teléfono.")
	@Size(max = 10, message = "El teléfono no debe superar los 10 caracteres.")
    private String numeroTelefono;
    
    @NotBlank(message = "Debe ingresar un género.")
	@Size(max = 15, message = "El teléfono no debe superar los 15 caracteres.")
    private String genero;
    
    @NotNull(message = "La fecha de inscripción no pueden estar vacía.")
    @PastOrPresent
    private Date fechaInscripcion;
    
    @NotBlank(message = "Debe ingresar un estado.")
	@Size(max = 10, message = "El teléfono no debe superar los 10 caracteres.")
    private String estado;
    
	@JsonIgnore
	@OneToMany(mappedBy = "alumno")
	private List<RegistroAsistencia> registrosAsistencia;

	@JsonIgnore
	@OneToMany(mappedBy = "alumno")
	private List<RegistroNotas> registrosNota;
    
	@JsonIgnore
    @ManyToMany(mappedBy = "alumnos")
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

	public List<RegistroAsistencia> getRegistrosAsistencia() {
		return registrosAsistencia;
	}

	public void setRegistrosAsistencia(List<RegistroAsistencia> registrosAsistencia) {
		this.registrosAsistencia = registrosAsistencia;
	}

	public List<RegistroNotas> getRegistrosNota() {
		return registrosNota;
	}

	public void setRegistrosNota(List<RegistroNotas> registrosNota) {
		this.registrosNota = registrosNota;
	}

	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}
    
    
}
