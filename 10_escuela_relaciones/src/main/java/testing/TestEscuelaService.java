package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.EscuelaService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})

public class TestEscuelaService {
	@Autowired
	EscuelaService service;
	
	@Test
	void testAlumnosCurso() {
		assertEquals(4,service.alumnosCurso("java").size());
	}
	@Test
	void testAlumnosCursoDuracion() {
		assertEquals(3,service.alumnosCursoDuracion(67).size());
	}
	@Test
	void testCursoMatriculadoAlumno() {
		assertEquals("java",service.cursoMatriculadoAlumno("1111A").getDenominacion());
	}
	@Test
	void testAlumnosSenior() {
		assertEquals(3,service.alumnosSenior(30).size());
	}
	@Test
	void testEdadMediaCurso() {
		assertEquals(25.0, service.edadMediaCurso("java"));
	}
	@Test
	void testPrecioCurso() {
		assertEquals(250, service.precioCurso("mvc@gmail.com"));
	}
	
}
