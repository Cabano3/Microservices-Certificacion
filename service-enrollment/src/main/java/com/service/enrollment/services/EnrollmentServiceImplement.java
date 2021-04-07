package com.service.enrollment.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.services.CommonServiceImplement;
import com.service.enrollment.models.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImplement extends CommonServiceImplement<Enrollment, EnrollmentRepository> implements EnrollmentService {

	@Override
	@Transactional(readOnly = true)
	public Iterable<Enrollment> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

}
