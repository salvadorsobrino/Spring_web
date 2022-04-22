package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	/**
	 * Método que da de alta un alumno que no existe en la bbdd (que no coincida el
	 * nombre)
	 * 
	 * @param Recibe un parametro a , es el alumno a dar de alta
	 */
	@Transactional // Utilizamos la de Spring NO la de javax.persistence
	@Override
	public void alta(Alumno a) {
		if (buscarNombre(a) == null) {
			entityManager.persist(a); // Entidad(no columnas) y Objeto. Abre y cierra una transaccion.
			// JpaTransactionManager
		}
	}

	/**
	 * Método que devuelve una lista de los alumnos del mismo curso
	 * 
	 * @param Recibe un String curso que es curso a buscar
	 * @return Devuelve la lista de alumnos
	 */
	@Override
	public List<Alumno> buscar(String curso) {
		String jpql = "select a from Alumno a where a.curso=:curso";
		//Otra forma: String jpql="select a from Alumno a where a.curso=?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		//De la otra forma: qr.setParameter(1,curso);
		qr.setParameter("curso", curso);
		// No hay que hacer casting
		List<Alumno> alumnos = qr.getResultList();

		return alumnos.size() > 0 ? alumnos : null;
	}

	/**
	 * Método que busca los distintos cursos que existen
	 * 
	 * @return Devuelve la lista de cursos distintos
	 */
	@Override
	public List<String> buscarCursos() {
		String jpql = "select distinct(a.curso) from Alumno a";
		TypedQuery<String> qr = entityManager.createQuery(jpql, String.class);
		// No hay que hacer casting
		List<String> cursos = qr.getResultList();
		return cursos.size() > 0 ? cursos : null;
	}

	/**
	 * Método para buscar por nombre a un alumno
	 * 
	 * @param recibe el alumno a buscar
	 * @return devuelve el alumno
	 */
	@Override
	public Alumno buscarNombre(Alumno a) {
		String jpql = "select a from Alumno a where nombre=:nombre";
		Query qr = entityManager.createQuery(jpql);
		qr.setParameter("nombre", a.getNombre());
		Alumno alumno = null;
		try {
			alumno = (Alumno) qr.getSingleResult(); //Si el alumno existe devuelve el alumno y sino devuelve null
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		return alumno;
	}

}
