package com.service.commonEnrollment.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="enrollment_day")
	private Date enrollmentDay;
	
	public Enrollment() {
	}

	@PrePersist
	public void prePersist() {
		this.enrollmentDay = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEnrollmentDay() {
		return enrollmentDay;
	}

	public void setEnrollmentDay(Date enrollmentDay) {
		this.enrollmentDay = enrollmentDay;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Enrollment)) {
			return false;
		}
		
		Enrollment am = (Enrollment) obj;
		return this.id != null && this.id.equals(am.getId());
	}
	
	
}
