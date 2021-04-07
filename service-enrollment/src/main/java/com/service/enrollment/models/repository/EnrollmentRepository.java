package com.service.enrollment.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.service.commonEnrollment.models.entity.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {

}
