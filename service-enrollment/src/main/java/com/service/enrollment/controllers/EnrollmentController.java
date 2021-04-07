package com.service.enrollment.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.controllers.CommonController;
import com.service.enrollment.services.EnrollmentService;

@RestController
public class EnrollmentController extends CommonController<Enrollment, EnrollmentService>{

	@GetMapping("/matriculas-por-alumno")
	public ResponseEntity<?> obtenerMatriculasPorAlumno(@RequestParam List<Long> ids){
		return ResponseEntity.ok(service.findAllById(ids));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Enrollment enrollment, @PathVariable Long id){
		Optional<Enrollment> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Enrollment enrollmentDb = o.get();
		
		enrollmentDb.getDetalles()
		.stream()
		.filter(ddb -> !enrollment.getDetalles().contains(ddb))
		.forEach(enrollmentDb::removeDetalles);
		
		enrollmentDb.setDetalles(enrollment.getDetalles());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(enrollmentDb));
	}
}
