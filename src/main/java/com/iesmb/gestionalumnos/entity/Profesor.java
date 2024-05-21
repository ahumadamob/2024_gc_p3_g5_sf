package com.iesmb.gestionalumnos.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Profesor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max = 25, message = "El nombre no puede superar los 25 caracteres.")
	private String nombre;
	
	@NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max = 25, message = "El apellido no puede superar los 25 caracteres.")
	private String apellido;
	
	@NotBlank(message = "La especialidad no puede estar vacía.")
	@Size(max = 40, message = "La especialidad no puede superar los 40 caracteres.")
	private String especialidad;
	
	@NotBlank(message = "El correo electronico no puede estar vacío.")
	@Size(max = 65, message = "El correo electronico no puede superar los 65 caracteres.")
	private String correoElectronico;
	
	@NotBlank(message = "La direccion no puede estar vacía.")
	@Size(max = 65, message = "La direccion no puede superar los 65 caracteres.")
	private String direccion;
	
	@NotBlank(message = "El numero de telefono no puede estar vacío.")
	@Size(max = 25, message = "El numero de telefono no puede superar los 25 caracteres.")
	private String numeroDeTelefono;
	
	@NotBlank(message = "El genero no puede estar vacío.")
	@Size(max = 25, message = "El genero no puede superar los 25 caracteres.")
	private String genero;
	
	@NotBlank(message = "La fecha de contratacion no puede estar vacía.")
	private LocalDate fechaDeContratacion;
	
	@NotBlank(message = "El estado no puede estar vacío.")
	@Size(max = 25, message = "El estado no puede superar los 25 caracteres.")
	private String estado;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;

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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
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

	public String getNumeroDeTelefono() {
		return numeroDeTelefono;
	}

	public void setNumeroDeTelefono(String numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getFechaDeContratacion() {
		return fechaDeContratacion;
	}

	public void setFechaDeContratacion(LocalDate fechaDeContratacion) {
		this.fechaDeContratacion = fechaDeContratacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	

	
}
