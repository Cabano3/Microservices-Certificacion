package com.service.courses.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.services.CommonService;
import com.service.courses.models.entity.Subject;

public interface SubjectService extends CommonService<Subject> {

	public List<Subject> findByNameOrNrc(String term);
	
	public List<Subject> findSubjectByMatriculaId(Long id);
	
	public Iterable<Enrollment> obtenerMatriculasPorAlumno(@RequestParam List<Long> ids);
}
