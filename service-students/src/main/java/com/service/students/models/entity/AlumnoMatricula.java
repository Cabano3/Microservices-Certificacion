package com.service.students.models.entity;

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
@Table(name="alumnos_matriculas")
public class AlumnoMatricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="matricula_id", unique = true)
	private Long matriculaId;
	
	@JsonIgnoreProperties(value= {"alumnosMatriculas"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="student_id")
	private Student student;

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof AlumnoMatricula)) {
			return false;
		}
		
		AlumnoMatricula am = (AlumnoMatricula) obj;
		return this.matriculaId != null && this.matriculaId.equals(am.getMatriculaId());
	}
	
	
}
