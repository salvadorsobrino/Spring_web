package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import model.Alumno;
import model.Curso;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestFormacionService {
	
	//Se prueban los metodos de la capa service no de la capa DAO
	
	@Autowired
	FormacionService service;
	
	@Test
	void tesValidarUsuario() {
		assertEquals("ordenador", service.validarUsuario("aaa", "sseeeee").getNombre());
	}
	@Test
	void testObtenerCursos() {
		assertEquals(2, service.obtenerCursos("aaa").size());
	}
	@Test
	void testListaCursos(){
		assertEquals(19, service.listaCursos().size());
		
	}
	@Test
	void testAlumnosCurso(){
		assertEquals(4, service.alumnosCurso("python").size());
		
	}
	/*@Test
	void testMatricularAlumno() {
		assertEquals(true, service.matricularAlumno("admin", 17));
		
	}*/
	@Test
	void testValidarAlumno() {
		assertNotNull(service.validarUsuario("admin","a"));
		assertNull(service.validarUsuario("text", "mytext"));
	}

	@Test
	void testAltaAlumno() {
		Alumno a = new Alumno("fran", "fran", "fran", 20, "fran@gmail.com");
		assertEquals(false, service.altaAlumno(a));
	}
	@Test
	void testAltaCurso() {
		Calendar c1 = Calendar.getInstance();
		c1.set(2020, 8, 20);
		Date f1 = c1.getTime();
		Curso c = new Curso("curso0",200,f1,200);
		assertEquals(false, service.altaCurso(c));
	}
	@Test
	void testListaCursosMatriculados() {
		assertNotNull(service.listaCursosNoMatriculados("diego armando"));
	}
	@Test 
	void testConsultaMatriculas() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.set(2020, 8, 20); //los meses del 0-11
		c2.set(2022, 2, 10);
		Date f1 = c1.getTime();
		Date f2 = c2.getTime();
		assertNotNull(service.consultarMatriculas(f1, f2));
	}
}
