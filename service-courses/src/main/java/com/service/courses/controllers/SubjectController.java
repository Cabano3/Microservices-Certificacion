package com.service.courses.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.commons.controllers.CommonController;
import com.service.courses.models.entity.Subject;
import com.service.courses.services.SubjectService;

@RestController
public class SubjectController extends CommonController<Subject, SubjectService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Subject subject, @PathVariable Long id){
		Optional<Subject> optional = this.service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Subject subjectDb = optional.get();
		subjectDb.setName(subject.getName());
		subjectDb.setNrc(subject.getNrc());
		subjectDb.setQuota(subject.getQuota());
		subjectDb.setTeacher(subject.getTeacher());
		subjectDb.setPrice(subject.getPrice());
		subjectDb.setHours(subject.getHours());
		subjectDb.setStatus(subject.getStatus());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(subjectDb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNameOrNrc(term));
		
	}
}
