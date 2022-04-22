package service;

import java.util.List;
import model.Alumno;


public interface AcademiaService {
	void alta(Alumno a);
	List <Alumno> buscar(String curso);
	List <String> buscarCursos();
	Alumno buscarNombre(Alumno a);
}
