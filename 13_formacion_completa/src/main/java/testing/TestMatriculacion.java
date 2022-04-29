package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestMatriculacion {
	@Autowired
	FormacionService service;
	
	@Test
	void testMatricularAlumno() {
		service.matricularAlumno("diego armando", 17);
		assertEquals(3, service.obtenerCursos("diego armando").size());
		//Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno
		assertEquals(5, service.alumnosCurso("kafka").size());
		//Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso
		
	}
}
