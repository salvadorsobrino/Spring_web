package service;

import java.util.List;

import model.Alumno;
import model.Curso;

public interface FormacionService {
	
	Alumno validarUsuario(String usuario, String password);
	//Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno
	List<Curso> obtenerCursos(String usuario);
	//Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno
	List<Curso> listaCursos();
	//Lista de cursos
	List<Alumno> alumnosCurso(String nombre);
	//Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso
	boolean matricularAlumno(String usuario,int idCurso);
	//Matricular alumno. A partir del usuario e idCurso, el alumno se matricula en dicho curso
	List<Alumno> listaAlumnos();
	//Lista de alumnos
	
}
