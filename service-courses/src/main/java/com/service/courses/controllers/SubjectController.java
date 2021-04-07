package com.service.courses.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.controllers.CommonController;
import com.service.courses.models.entity.MateriaMatricula;
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
	
	@GetMapping
	@Override
	public ResponseEntity<?> list(){
		List<Subject> students = ((List<Subject>) service.findAll()).stream().map(s -> {
			s.getMateriasMatriculas().forEach(am -> {
				Enrollment enrollment = new Enrollment();
				enrollment.setId(am.getMatriculaId());
				s.addMatricula(enrollment);
			});
			return s;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(students);
	}
	
	@PutMapping("/{id}/asignar-matricula")
	public ResponseEntity<?> asignarMatricula(@RequestBody List<Enrollment> enrollment, @PathVariable Long id){
		Optional<Subject> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Subject studentDb = o.get();
		
		enrollment.forEach(e ->{
			MateriaMatricula alumnoMatricula = new MateriaMatricula();
			alumnoMatricula.setMatriculaId(e.getId());
			alumnoMatricula.setSubject(studentDb);
			studentDb.addMateriasMatriculas(alumnoMatricula);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(studentDb));
	}
	
	@GetMapping("/matricula/{id}")
	public ResponseEntity<?> buscarPorMatriculaId(@PathVariable Long id){
		List<Subject> student = service.findSubjectByMatriculaId(id);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> detail(@PathVariable Long id){
		Optional<Subject> opt = service.findById(id);
		if(opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Subject student = opt.get();
		if(student.getMateriasMatriculas().isEmpty() == false) {
			List<Long> ids = student.getMateriasMatriculas().stream().map(am -> am.getMatriculaId())
					.collect(Collectors.toList());
			
			List<Enrollment> enrollment = (List<Enrollment>) service.obtenerMatriculasPorAlumno(ids);
			
			student.setMatriculas(enrollment);
		}
		return ResponseEntity.ok().body(student);
	}
	
}
