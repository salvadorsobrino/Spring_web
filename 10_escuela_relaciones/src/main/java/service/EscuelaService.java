package service;

import java.util.List;

import model.Alumno;
import model.Curso;

public interface EscuelaService {
	List<Alumno> alumnosCurso(String nombre); //Por nombre del curso
	List<Alumno> alumnosCursoDuracion(int duracionMax); //Por duracion maxima del curso
	Curso cursoMatriculadoAlumno(String dni); //Saber el curso en el que esta matriculado un alumno
	List<Curso> alumnosSenior(int edad); //Lista de cursos cuyos alumnos matriculados tienen edad superior al valor recibido
	double edadMediaCurso(String nombreCurso); //Devuelve la edad media de los alumnos en el curso indicado
	double precioCurso(String email);//Devuelve el precio del curso en el que esta matriculado el alumno cuyo email se recibe

}
