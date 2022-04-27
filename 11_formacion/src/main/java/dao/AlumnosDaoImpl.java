package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;
import model.Curso;
@Repository //Mismo objetivo que @Service pero en clases DAO (es otra capa). Tienes que instanciar esta clase.
public class AlumnosDaoImpl implements AlumnosDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Alumno findByUsuarioAndPassword(String usuario, String password) {
		String jpql = "select a from Alumno a where a.usuario=?1 and a.password=?2";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, usuario);
		qr.setParameter(2, password);
		List<Alumno> alumnos = qr.getResultList();

		return alumnos.size() > 0 ? alumnos.get(0) : null;
	}

	@Override
	public List<Alumno> findByCurso(String nombreCurso) {
		String jpql = "select a from Alumno a join a.cursos c where c.nombre=?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, nombreCurso);
		List<Alumno> alumnos = qr.getResultList();

		return alumnos.size() > 0 ? alumnos : null;
	}

	@Override
	public Alumno findById(String usuario) {
		return entityManager.find(Alumno.class, usuario);
	}
	@Transactional //???
	@Override
	public void update(Alumno alumno) {
		entityManager.merge(alumno);
	}

	@Override
	public List<Alumno> findAll() {
		String jpql = "select a from Alumno a";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		List<Alumno> alumnos = qr.getResultList();

		return  alumnos.size() > 0 ? alumnos : null;
	}

}
