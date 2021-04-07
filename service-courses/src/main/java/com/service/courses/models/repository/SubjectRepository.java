package com.service.courses.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.service.courses.models.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long>{

	@Query("select s from Subject s where s.name like %?1% or s.nrc like %?1%")
	public List<Subject> findByNameOrNrc(String term);
	
}
