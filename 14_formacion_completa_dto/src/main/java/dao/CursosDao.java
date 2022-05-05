package dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	//Curso findById(int idCurso);
	//List<Curso> findAll();
	@Query("select c from Curso c join c.alumnos a where a.usuario=?1")
	List<Curso>findByAlumno(String usuario);
	Optional<Curso> findByNombre(String nombre);
	//Metodo para buscar curso por nombre
	@Query("select c from Curso c where c.idCurso NOT IN (select c.idCurso from Curso c join c.alumnos a where a.usuario=?1)")
	//@Query("select c from Curso c join c.alumnos a where a.usuario=?1 NOT IN (select c from Curso c)")
	//"select c from Curso c where c Not In (Select c From Curso c join c.alumnos a Where a.usuario=?1)"
	List<Curso>cursosNoMatriculados(String usuario);
	List<Curso>findByFechaInicioBetween(Date desde, Date hasta);
	//select c from Curso c where c.fechaInicio between ?1 and ?2
	//No hace falta query gracias a la palabra reservada between
}
