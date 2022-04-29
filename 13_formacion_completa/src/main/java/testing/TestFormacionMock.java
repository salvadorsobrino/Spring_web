package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;
import service.FormacionService;
import service.FormacionServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class }) 
public class TestFormacionMock {
	@Mock
	AlumnosDao alumnosDao;
	
	@Mock
	CursosDao cursosDao;
	
	List<Alumno> alumnos;
	List<Curso> cursos;
	
	FormacionService service;
	
	@BeforeEach
	void init() {
		cursos=List.of(new Curso(1,"curso1",100,null,10),
				new Curso(2,"curso2",200,null,20),
				new Curso(3,"curso3",300,null,30));
		alumnos=List.of(new Alumno("user1","pwd1","n1",10,"e1",new ArrayList<>(List.of(cursos.get(0),cursos.get(1)))),
				new Alumno("user2","pwd2","n2",20,"e2",new ArrayList<>(List.of(cursos.get(0)))),
				new Alumno("user3","pwd3","n3",30,"e3",new ArrayList<>(List.of(cursos.get(2)))),
				new Alumno("user4","pwd4","n4",10,"e4",new ArrayList<>(List.of(cursos.get(0),cursos.get(2)))));
		lenient().when(alumnosDao.findByUsuarioAndPassword("user1", "pwd1")).thenReturn(alumnos.get(0));
		lenient().when(alumnosDao.findByUsuarioAndPassword("user3", "pwd3")).thenReturn(alumnos.get(2));
		lenient().when(alumnosDao.findByUsuarioAndPassword("user7", "aaa")).thenReturn(null);
		lenient().when(alumnosDao.findByCurso("curso1")).thenReturn(List.of(alumnos.get(0),alumnos.get(1),alumnos.get(3)));
		lenient().when(alumnosDao.findById("user3")).thenReturn(Optional.of(alumnos.get(2)));
		//Llamando al metodo Optional.of encapsulamos el objeto
		//lenient().doNothing().when(alumnosDao).save(alumnos.get(2)); //para que no haga nada al llamar a update
		lenient().when(alumnosDao.save(alumnos.get(2))).thenReturn(alumnos.get(2));
		//doNothing() ahora devuelve un alumno
		lenient().when(cursosDao.findById(2)).thenReturn(Optional.of(cursos.get(1)));
		lenient().when(cursosDao.findAll()).thenReturn(cursos);
		lenient().when(cursosDao.findByAlumno("user3")).thenReturn(alumnos.get(2).getCursos());
		
		service=new FormacionServiceImpl(alumnosDao,cursosDao);
	}
	
	@Test
	void testBuscarAlumno() {
		assertEquals("e1",service.validarUsuario("user1", "pwd1").getEmail());
		assertNull(service.validarUsuario("user7", "aaa"));
	}
	
	@Test
	void testCursosAlumno() {
		assertEquals(1, service.obtenerCursos("user3").size());
		
	}
	@Test
	void testAlumnosCurso() {
		assertEquals(3, service.alumnosCurso("curso1").size());
	}
	
	@Test
	void testCursos() {
		assertEquals(3, service.listaCursos().size());
	}
	
	@Test
	void testMatricular() {
		assertTrue(service.matricularAlumno("user3", 2));
		//tras matricular al alumno en un nuevo curso, obtenemos el alumno
		//y comprobamos que tiene un curso m�s al creado inicialmente
		assertEquals(2,service.validarUsuario("user3", "pwd3").getCursos().size());
	}
	
}
