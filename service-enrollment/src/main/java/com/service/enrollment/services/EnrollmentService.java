package com.service.enrollment.services;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.services.CommonService;

public interface EnrollmentService extends CommonService<Enrollment>{

	public Iterable<Enrollment> findAllById(Iterable<Long> ids);
}
