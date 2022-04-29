package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Alumno;
//Añadimos JpaRepository<Entidad, PK>
public interface AlumnosDao extends JpaRepository<Alumno,String>{
	// Aplicando Spring Data JPA
	Alumno findByUsuarioAndPassword(String usuario,String password); //por llamarlo de esa manera JpaRepository sabe como hacerlo
	@Query("select a from Alumno a join a.cursos c where c.nombre=?1") //ni ResultList ni nada, ya sabe lo que tiene que hacer
	List<Alumno> findByCurso(String nombreCurso);
	//Alumno findById(String usuario); ya te lo hace JpaRepository
	//void update(Alumno alumno); ya te lo hace JpaRepository
	//List<Alumno> findAll(); ya te lo hace JpaRepository
}
