package com.service.commonEnrollment.models.entity;

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
@Table(name="enrollments_Details")
public class EnrollmentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="status")
	private String status;
	
	@JsonIgnoreProperties(value = {"detalles"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="enrollment_id")
	private Enrollment enrollment;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof EnrollmentDetails)) {
			return false;
		}
		
		EnrollmentDetails ed = (EnrollmentDetails) obj;
		
		return this.id != null && this.id.equals(ed.getId());
	}	
}
