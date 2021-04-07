package com.service.students.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.commonEnrollment.models.entity.Enrollment;

@FeignClient(name="service-enrollment")
public interface MatriculaFeignClient {

	@GetMapping("/matriculas-por-alumno")
	public Iterable<Enrollment> obtenerMatriculasPorAlumno(@RequestParam List<Long> ids);
}
