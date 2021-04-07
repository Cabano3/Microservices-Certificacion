package com.service.payment.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.service.courses.models.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
