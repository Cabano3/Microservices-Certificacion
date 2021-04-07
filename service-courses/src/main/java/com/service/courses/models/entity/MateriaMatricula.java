package com.service.courses.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="materia_matriculas")
public class MateriaMatricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="matricula_id")
	private Long matriculaId;
	
	@JsonIgnoreProperties(value= {"materiasMatriculas"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="subject_id")
	private Subject subject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatriculaId() {
		return matriculaId;
	}

	public void setMatriculaId(Long matriculaId) {
		this.matriculaId = matriculaId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof MateriaMatricula)) {
			return false;
		}
		
		MateriaMatricula am = (MateriaMatricula) obj;
		return this.matriculaId != null && this.matriculaId.equals(am.getMatriculaId());
	}
}
