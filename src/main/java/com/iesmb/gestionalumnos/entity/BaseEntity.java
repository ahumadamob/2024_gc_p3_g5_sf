package com.iesmb.gestionalumnos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime createdDate;
	
	@Column(name = "fecha_actualizacion")
	private LocalDateTime updateDate;
	
	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updateDate = LocalDateTime.now();
	}

	@PostPersist
	protected void afterCreate() {
	    System.out.println("Entidad creada con ID: " + getId());
	}

	@PostUpdate
	protected void afterUpdate() {
	    System.out.println("Entidad actualizada con ID: " + getId());
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
}
