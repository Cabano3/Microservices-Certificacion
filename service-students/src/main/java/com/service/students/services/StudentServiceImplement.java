package com.service.students.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.services.CommonServiceImplement;
import com.service.students.clients.MatriculaFeignClient;
import com.service.students.models.entity.Student;
import com.service.students.models.repository.StudentRepository;

@Service
public class StudentServiceImplement extends CommonServiceImplement<Student, StudentRepository > implements StudentService {

	@Autowired
	private MatriculaFeignClient clientMatricula;
	
	@Override
	@Transactional(readOnly = true)
	public Student findStudentByMatriculaId(Long id) {
		return repository.findStudentByMatriculaId(id);
	}

	@Override
	public Iterable<Enrollment> obtenerMatriculasPorAlumno(List<Long> ids) {
		return clientMatricula.obtenerMatriculasPorAlumno(ids);
	}

}
