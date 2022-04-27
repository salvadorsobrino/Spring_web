package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import model.Alumno;
import model.Curso;

@Service
public class EscuelaServiceImpl implements EscuelaService {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Alumno> alumnosCurso(String nombre) {
		String jpql = "select a from Alumno a where a.curso.denominacion=?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, nombre);
		List<Alumno> alumnos = qr.getResultList();
		
		return alumnos.size() > 0 ? alumnos : null;
	}

	@Override
	public List<Alumno> alumnosCursoDuracion(int duracionMax) {
		String jpql = "select a from Alumno a where a.curso.duracion<=?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, duracionMax);
		List<Alumno> alumnos = qr.getResultList();
		
		return alumnos.size() > 0 ? alumnos : null;
	}

	@Override
	public Curso cursoMatriculadoAlumno(String dni) {
		// String jpql = "select c from Curso c where c.alumnos.dni=?1";
		// No se puede hacer ya que alumnos es una coleccion. Entonces hay que utilizar.
		// JOIN EXPLICITO. Establecer un alias a cada alumno (a)
		String jpql = "select c from Curso c join c.alumnos a where a.dni=?1";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		qr.setParameter(1, dni);
		List<Curso> cursos = qr.getResultList();
		
		return cursos.size()>0?cursos.get(0):null;
	}

	@Override
	public List<Curso> alumnosSenior(int edad) {
		//OJO SON CURSOS NO ALUMNOS, ELIMINAR DUPLICADOS
		// JOIN EXPLICITO. Establecer un alias a cada alumno (a)
		String jpql = "select distinct(c) from Curso c join c.alumnos a where a.edad>=?1";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		qr.setParameter(1, edad);
		List<Curso> cursos = qr.getResultList();
		
		return cursos.size() > 0 ? cursos : null;
	}

	@Override
	public double edadMediaCurso(String nombreCurso) {
		String jpql = "select avg(a.edad) from Alumno a where a.curso.denominacion=?1";
		TypedQuery<Double> qr = entityManager.createQuery(jpql, Double.class);
		qr.setParameter(1, nombreCurso);
		
		return qr.getSingleResult();
	}

	@Override
	public double precioCurso(String email) {
		String jpql = "select c.precio from Curso c join c.alumnos a where a.email=?1";
		TypedQuery<Double> qr = entityManager.createQuery(jpql, Double.class);
		qr.setParameter(1, email);
		
		return qr.getSingleResult();
	}

}
