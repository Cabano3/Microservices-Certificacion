package com.service.students.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.controllers.CommonController;
import com.service.students.models.entity.AlumnoMatricula;
import com.service.students.models.entity.Student;
import com.service.students.services.StudentService;

@RestController
public class StudentController extends CommonController<Student, StudentService> {
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@GetMapping
	@Override
	public ResponseEntity<?> list() {
		List<Student> students = ((List<Student>) service.findAll()).stream().map(s -> {
			s.getAlumnosMatriculas().forEach(am -> {
				Enrollment enrollment = new Enrollment();
				enrollment.setId(am.getMatriculaId());
				s.addMatricula(enrollment);
			});
			return s;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(students);
	}
	
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("students", service.findAll());
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Long id){
		Optional<Student> optional = service.findById(id);
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Student studentDb = optional.get();
		studentDb.setFirstName(student.getFirstName());
		studentDb.setLastName(student.getLastName());
		studentDb.setPlaceOfBirth(student.getPlaceOfBirth());
		studentDb.setBirth(student.getBirth());
		studentDb.setNumberCardId(student.getNumberCardId());
		studentDb.setPhone(student.getPhone());
		studentDb.setSex(student.getSex());
		studentDb.setEmail(student.getEmail());
		studentDb.setAddress(student.getAddress());
		studentDb.setStartFrom(student.getStartFrom());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
	}
	
	@PutMapping("/{id}/asignar-matricula")
	public ResponseEntity<?> asignarMatricula(@RequestBody List<Enrollment> enrollment, @PathVariable Long id){
		Optional<Student> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Student studentDb = o.get();
		
		enrollment.forEach(e ->{
			AlumnoMatricula alumnoMatricula = new AlumnoMatricula();
			alumnoMatricula.setMatriculaId(e.getId());
			alumnoMatricula.setStudent(studentDb);
			studentDb.addAlumnosMatriculas(alumnoMatricula);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(studentDb));
	}
	
	@PutMapping("/{id}/eliminar-matricula")
	public ResponseEntity<?> eliminarMatricula(@RequestBody Enrollment enrollment, @PathVariable Long id){
		Optional<Student> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Student studentDb = o.get();
		
		AlumnoMatricula alumnoMatricula = new AlumnoMatricula();
		alumnoMatricula.setMatriculaId(enrollment.getId());
		studentDb.removeAlumnosMatriculas(alumnoMatricula);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(studentDb));
	}
	
	@GetMapping("/matricula/{id}")
	public ResponseEntity<?> buscarPorMatriculaId(@PathVariable Long id){
		Student student = service.findStudentByMatriculaId(id);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> detail(@PathVariable Long id){
		Optional<Student> opt = service.findById(id);
		if(opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Student student = opt.get();
		if(student.getAlumnosMatriculas().isEmpty() == false) {
			List<Long> ids = student.getAlumnosMatriculas().stream().map(am -> am.getMatriculaId())
					.collect(Collectors.toList());
			
			List<Enrollment> enrollment = (List<Enrollment>) service.obtenerMatriculasPorAlumno(ids);
			
			student.setMatriculas(enrollment);
		}
		return ResponseEntity.ok().body(student);
	}
	

}
