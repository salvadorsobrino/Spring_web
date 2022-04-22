package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;

@Service
public class AcademiaServiceImpl implements AcademiaService {

	/*
	 * @Autowired JdbcTemplate template;
	 */

	// Ahora utilizando JPA javax.persistence
	// PersistenceContext es como Autowired es para que Spring te lo integre, ya
	// esta preparado para la bbdd

	@PersistenceContext
	EntityManager entityManager;

	@Transactional // Utilizamos la de Spring NO la de javax.persistence
	@Override
	public void alta(Alumno a) {
		entityManager.persist(a); // Entidad(no columnas) y Objeto. Abre y cierra una transaccion.
		// JpaTransactionManager

	}

	@Override
	public List<Alumno> buscar(String curso) {
		String jpql = "select a from Alumno a where a.curso=:curso";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter("curso", curso);
		// No hay que hacer casting
		List<Alumno> alumnos = qr.getResultList();

		return alumnos;
	}

	@Override
	public List<String> buscarCursos() {
		String jpql = "select distinct(a.curso) from Alumno a";
		TypedQuery<String> qr = entityManager.createQuery(jpql, String.class);
		// No hay que hacer casting
		List<String> cursos = qr.getResultList();
		return cursos;
	}

	@Override
	public Alumno buscarNombre(Alumno a) {
		String jpql = "select a from Alumno a where nombre=:nombre";
		Query qr = entityManager.createQuery(jpql);
		qr.setParameter("nombre", a.getNombre());
		return (Alumno) qr.getSingleResult();
	}

}
