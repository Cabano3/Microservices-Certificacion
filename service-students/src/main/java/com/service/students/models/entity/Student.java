package com.service.students.models.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.commonEnrollment.models.entity.Enrollment;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@Column(name="id_student")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long idStudent;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="place_of_birth")
	private String placeOfBirth;
	
	@Column(name="birth")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar birth;
	
	@Column(name="number_card_Id")
	private String numberCardId;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address", length= 255)
	private String address;
	
	@Column(name="start_from")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar startFrom;
	
	@JsonIgnoreProperties(value= {"student"}, allowSetters = true)
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AlumnoMatricula> alumnosMatriculas;
	
	@Transient
	private List<Enrollment> matriculas;
	
	public Student() {
		this.alumnosMatriculas = new ArrayList<>();
		this.matriculas = new ArrayList<>();
	}
	
	//getters and setters
	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(Calendar birth) {
		this.birth = birth;
	}

	public String getNumberCardId() {
		return numberCardId;
	}

	public void setNumberCardId(String numberCardId) {
		this.numberCardId = numberCardId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(Calendar startFrom) {
		this.startFrom = startFrom;
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

	public List<AlumnoMatricula> getAlumnosMatriculas() {
		return alumnosMatriculas;
	}

	public void setAlumnosMatriculas(List<AlumnoMatricula> alumnosMatriculas) {
		this.alumnosMatriculas = alumnosMatriculas;
	}
	
	public void addAlumnosMatriculas(AlumnoMatricula alumnoMatricula) {
		this.alumnosMatriculas.add(alumnoMatricula);
	}
	
	public void removeAlumnosMatriculas(AlumnoMatricula alumnoMatricula) {
		this.alumnosMatriculas.remove(alumnoMatricula);
	}
	
	
}
