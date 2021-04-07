package com.service.courses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.services.CommonServiceImplement;
import com.service.courses.clients.MatriculaFeignClient;
import com.service.courses.models.entity.Subject;
import com.service.courses.models.repository.SubjectRepository;

@Service
public class SubjectServiceImplement extends CommonServiceImplement<Subject, SubjectRepository> implements SubjectService {

	@Autowired
	private MatriculaFeignClient clientMatricula;
	
	@Override
	@Transactional(readOnly = true)
	public List<Subject> findByNameOrNrc(String term) {
		return repository.findByNameOrNrc(term);
	}

	@Override
	public List<Subject> findSubjectByMatriculaId(Long id) {
		return repository.findSubjectByMatriculaId(id);
	}

	@Override
	public Iterable<Enrollment> obtenerMatriculasPorAlumno(List<Long> ids) {
		return clientMatricula.obtenerMatriculasPorAlumno(ids);
	}


}
