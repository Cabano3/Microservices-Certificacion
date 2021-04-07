package com.service.courses.models.entity;

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
@Table(name="payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_day")
	private Date paymentDay;
	
	private double total;
	
	private String status;
	
	@JsonIgnoreProperties(value = {"payment"}, allowSetters = true)
	@OneToMany(mappedBy = "payment",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentDetails> detalles;
	
	@PrePersist
	public void prePersist() {
		this.paymentDay = new Date();
		
		this.detalles.forEach(e -> {
			this.total += e.getSubtotal();
		});
	}
	
	public Payment() {
		this.detalles = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Date paymentDay) {
		this.paymentDay = paymentDay;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PaymentDetails> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<PaymentDetails> detalles) {
		this.detalles.clear();
		detalles.forEach(this::addDetalles);
	}
	
	public void addDetalles(PaymentDetails detalle) {
		this.detalles.add(detalle);
		detalle.setPayment(this);
	}
}
