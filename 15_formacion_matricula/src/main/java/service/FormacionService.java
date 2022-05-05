package service;

import java.util.Date;
import java.util.List;

import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import model.Alumno;
import model.Curso;

public interface FormacionService 
{
	AlumnoDto validarUsuario(String usuario, String password);
	//Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno
	List<CursoDto> obtenerCursos(String usuario);
	//Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno
	List<CursoDto> listaCursos();
	//Lista de cursos
	List<AlumnoDto> alumnosCurso(String nombre);
	//Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso
	boolean matricularAlumno(String usuario,int idCurso);
	//Matricular alumno. A partir del usuario e idCurso, el alumno se matricula en dicho curso
	List<AlumnoDto> listaAlumnos();
	//Lista de alumnos
	boolean altaAlumno(AlumnoDto a);
	//Dar de alta un alumno
	boolean altaCurso(CursoDto c);
	//Dar de alta un curso
	
	List<MatriculaDto> consultarMatriculas(Date f1,Date f2);
	List<CursoDto> listaCursosNoMatriculados(String usuario);
}
