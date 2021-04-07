package com.service.courses.services;

import java.util.List;

import com.service.commons.services.CommonService;
import com.service.courses.models.entity.Subject;

public interface SubjectService extends CommonService<Subject> {

	public List<Subject> findByNameOrNrc(String term);
}
