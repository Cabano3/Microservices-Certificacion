package com.service.payment.services;

import org.springframework.stereotype.Service;

import com.service.commons.services.CommonServiceImplement;
import com.service.courses.models.entity.Payment;
import com.service.payment.models.repository.PaymentRepository;

@Service
public class PaymentServiceImplement extends CommonServiceImplement<Payment, PaymentRepository> implements PaymentService {

}
