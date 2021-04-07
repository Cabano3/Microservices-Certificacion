package com.service.students.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.service.commonEnrollment.models.entity.Enrollment;
import com.service.commons.services.CommonService;
import com.service.students.models.entity.Student;

public interface StudentService extends CommonService<Student>{

	public Student findStudentByMatriculaId(Long id);

	public Iterable<Enrollment> obtenerMatriculasPorAlumno(@RequestParam List<Long> ids);

}
