package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
		assertEquals(18, service.listaCursos().size());
		
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
	void validarAlumno() {
		assertNotNull(service.validarUsuario("admin","a"));
		assertNull(service.validarUsuario("text", "mytext"));
	}
}
