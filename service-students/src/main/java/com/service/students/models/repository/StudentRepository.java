package com.service.students.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.service.students.models.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	@Query("SELECT s FROM Student s join fetch s.alumnosMatriculas a where a.matriculaId =?1")
	public Student findStudentByMatriculaId(Long id);
}
