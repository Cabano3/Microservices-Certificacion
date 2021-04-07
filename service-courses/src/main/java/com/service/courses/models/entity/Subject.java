package com.service.courses.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.commonEnrollment.models.entity.Enrollment;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="nrc")
	private String nrc;
	
	@Column(name="teacher")
	private String teacher;
	
	@Column(name="quota")
	private int quota;
	
	private double price;
	
	private int hours;
	
	private String status;
	
	@JsonIgnoreProperties(value= {"subject"}, allowSetters = true)
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MateriaMatricula> materiasMatriculas;
	
	@Transient
	private List<Enrollment> matriculas;

	public Subject() {
		this.materiasMatriculas = new ArrayList<>();
		this.matriculas = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MateriaMatricula> getMateriasMatriculas() {
		return materiasMatriculas;
	}

	public void setMateriasMatriculas(List<MateriaMatricula> materiasMatriculas) {
		this.materiasMatriculas = materiasMatriculas;
	}

	public List<Enrollment> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Enrollment> matriculas) {
		this.matriculas = matriculas;
	}
	
	public void addMatricula(Enrollment matricula) {
		this.matriculas.add(matricula);
	}
	
	public void removeMatricula(Enrollment matricula) {
		this.matriculas.remove(matricula);
	}
	
	public void addMateriasMatriculas(MateriaMatricula materiaMatricula) {
		this.materiasMatriculas.add(materiaMatricula);
	}
	
	public void removeMateriasMatriculas(MateriaMatricula materiaMatricula) {
		this.materiasMatriculas.remove(materiaMatricula);
	}
	
	
	
	
	
	
}
