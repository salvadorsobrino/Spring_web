package service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;

@Service
public class FormacionServiceImpl implements FormacionService {
	
	//@PersistenceContext
	//EntityManager entityManager;
	
	//Ahora aplicando la capa DAO
	//@Autowired
	AlumnosDao alumnosDao;
	//@Autowired
	CursosDao cursosDao;
	
	//Ahora mediante Constructor (MOCK)
	public FormacionServiceImpl(@Autowired AlumnosDao alumnosDao, @Autowired CursosDao cursosDao) {
		this.alumnosDao = alumnosDao;
		this.cursosDao = cursosDao;
	} // De esta manera, de cara a a las pruebas podemos decidir inyectarles otros objetos, suplantadores.
	
	/**
	 * Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno
	 * 
	 * @param el usuario y la contraseña del alumno
	 * @return los datos del alumno completos
	 */
	@Override
	public Alumno validarUsuario(String usuario, String password) {
		return alumnosDao.findByUsuarioAndPassword(usuario, password);
	}
	/**
	 * Cursos de un alumno. A partir del usuario, obtener la lista de cursos en
	 * donde está matriculado en alumno
	 * 
	 * @param el usuario del alumno
	 * @return la lista de cursos matriculados
	 */
	@Override
	public List<Curso> obtenerCursos(String usuario) {
		return cursosDao.findByAlumno(usuario);
	}
	/**
	 * Lista de cursos
	 * 
	 * @return devuelve la Lista de cursos
	 */
	@Override
	public List<Curso> listaCursos() {
		return cursosDao.findAll();
	}
	/**
	 * Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos
	 * matriculados en dicho curso
	 * 
	 * @param el usuario del alumno
	 * @return los alumnos matriculados
	 */
	@Override
	public List<Alumno> alumnosCurso(String nombre) {
		return alumnosDao.findByCurso(nombre);
	}

	/**
	 * Matricular alumno. A partir del usuario e idCurso, el alumno se matricula en
	 * dicho curso
	 * 
	 * @param el usuario del alumno y el id del curso
	 */
	
	//NO ES TRANSACCIONAL, ES EL METODO DONDE SE REALIZA UNA OPERACION BASE DE DATOS. 
	@Transactional
	@Override
	public boolean matricularAlumno(String usuario, int idCurso) {
		//https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html
		//Clase para encapsular datos, en vez de toma Alumno, es toma Option <Alumno> para informar de si ha habido un resultado
		//Evitar que devuelva null para evitar NullPointer, te lo devuelve encapsulado en un Optional. Te devuelve vacio NO NULL.
		//isEmpty() isPresent() si hay resultado. metodo get() te devuelve el contenido encapsulado T. 
		//orElse() hace get() y dato alternativo si no hay resultado.
		
		/*Alumno alumno = alumnosDao.findById(usuario).orElse(null);
		Curso curso = cursosDao.findById(idCurso).orElse(null); //Logica de negocio.
		if (alumno!=null && curso!=null) {
			alumno.getCursos().add(curso);
			//alumnosDao.update(alumno);
			alumnosDao.save(alumno);
			return true;
		}
		return false;*/
		
		Optional<Alumno> alumno = alumnosDao.findById(usuario);
		Optional<Curso> curso = cursosDao.findById(idCurso); //Logica de negocio.
		if (alumno.isPresent() && curso.isPresent()) {
			Alumno al = alumno.get();
			al.getCursos().add(curso.get());
			alumnosDao.save(al);
			return true;
		}
		return false;
		
	}
	/**
	 * Lista de alumnos
	 * 
	 * @return devuelve la Lista de alumnos
	 */
	@Override
	public List<Alumno> listaAlumnos() {
		return alumnosDao.findAll();
	}

}
