package com.service.courses.services;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.commons.services.CommonServiceImplement;
import com.service.courses.models.entity.Subject;
import com.service.courses.models.repository.SubjectRepository;

@Service
public class SubjectServiceImplement extends CommonServiceImplement<Subject, SubjectRepository> implements SubjectService {

	@Override
	@Transactional(readOnly = true)
	public List<Subject> findByNameOrNrc(String term) {
		return repository.findByNameOrNrc(term);
	}


}
