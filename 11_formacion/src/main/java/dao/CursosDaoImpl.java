package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import model.Alumno;
import model.Curso;
@Repository //Mismo objetivo que @Service pero en clases DAO (es otra capa). Tienes que instanciar esta clase.
public class CursosDaoImpl implements CursosDao{
	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public Curso findById(int idCurso) {
		return entityManager.find(Curso.class, idCurso);
	}

	@Override
	public List<Curso> findAll() {
		String jpql = "select distinct(c) from Curso c";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		List<Curso> cursos = qr.getResultList();

		return cursos.size() > 0 ? cursos : null;
	}

	@Override
	public List<Curso> findByAlumno(String usuario) {
		String jpql = "select c from Curso c join c.alumnos a where a.usuario=?1";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		qr.setParameter(1, usuario);
		List<Curso> cursos = qr.getResultList();

		return cursos.size() > 0 ? cursos : null;
	}

}
