package com.service.payment.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.commons.controllers.CommonController;
import com.service.courses.models.entity.Payment;
import com.service.payment.services.PaymentService;

@RestController
public class PaymentController extends CommonController<Payment, PaymentService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Payment payment, @PathVariable Long id){
		Optional<Payment> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Payment paymentDb = o.get();
		paymentDb.setStatus(payment.getStatus());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(paymentDb));
	}
}
