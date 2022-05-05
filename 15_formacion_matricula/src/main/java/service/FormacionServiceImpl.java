package service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityDto;
import dao.AlumnosDao;
import dao.CursosDao;
import dao.MatriculasDao;
import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import model.Alumno;
import model.Curso;
import model.Matricula;
import model.MatriculaPk;
@Service
public class FormacionServiceImpl implements FormacionService {
	
	@Autowired
	ConversorEntityDto conversor;
	
	AlumnosDao alumnosDao;
	
	
	CursosDao cursosDao;
	
	MatriculasDao matriculasDao; 


	
	public FormacionServiceImpl(@Autowired AlumnosDao alumnosDao,@Autowired CursosDao cursosDao, @Autowired MatriculasDao matriculasDao) {
		this.alumnosDao=alumnosDao;
		this.cursosDao=cursosDao;
		this.matriculasDao=matriculasDao;
	}

	@Override
	public AlumnoDto validarUsuario(String usuario, String password) {
		return conversor.alumnoToDto(alumnosDao.findByUsuarioAndPassword(usuario, password));
	}

	@Override
	public List<CursoDto> obtenerCursos(String usuario) {
		return cursosDao.findByAlumno(usuario)
				.stream()
				.map(c->conversor.cursoToDto(c))
				.collect(Collectors.toList());
	}

	@Override
	public List<CursoDto> listaCursos() {
		return cursosDao.findAll()
				.stream()
				.map(c->conversor.cursoToDto(c))
				.collect(Collectors.toList());
	}

	@Override
	public List<AlumnoDto> alumnosCurso(String nombre) {
		return alumnosDao.findByCurso(nombre)
				.stream()
				.map(a->conversor.alumnoToDto(a))
				.collect(Collectors.toList());
	}

	@Override
	public boolean matricularAlumno(String usuario, int idCurso) {
		/*CursoDto curso=cursosDao.findById(idCurso).orElse(null);
		Alumno alumno=alumnosDao.findById(usuario).orElse(null);
		if(curso!=null&&alumno!=null) {
			alumno.getCursos().add(curso);
			alumnosDao.save(alumno);
			return true;
		}
		return false;*/
		
		
		Optional<Curso> curso=cursosDao.findById(idCurso);
		Optional<Alumno> alumno=alumnosDao.findById(usuario);
		if(curso.isPresent()&&alumno.isPresent()) {
			matriculasDao.save(new Matricula(new MatriculaPk(idCurso,usuario),0,curso.get(),alumno.get()));
			//haria falta pasar como parametro la nota y no es necesario pasar curso y alumno
			
			return true;
		}
		return false;
		
	}
	
	
	@Override
	public List<AlumnoDto> listaAlumnos() {
		return alumnosDao.findAll()
				.stream()
				.map(a->conversor.alumnoToDto(a))
				.collect(Collectors.toList());
	}

	@Override
	public boolean altaAlumno(AlumnoDto alumno) {
		if(alumnosDao.findById(alumno.getUsuario()).isEmpty()) {
			alumnosDao.save(conversor.dtoToAlumno(alumno));
			return true;
		}
		return false;
	}

	@Override
	public boolean altaCurso(CursoDto curso) {
		if(cursosDao.findByNombre(curso.getNombre()).isEmpty()) {
			cursosDao.save(conversor.dtoToCurso(curso));
			return true;
		}
		return false;
	}

	@Override
	public List<MatriculaDto> consultarMatriculas(Date f1, Date f2) {
		return matriculasDao.findMatriculaFechas(f1, f2)
				.stream()
				.map(m->conversor.matriculaToDto(m))
				.collect(Collectors.toList());
	}

	@Override
	public List<CursoDto> listaCursosNoMatriculados(String usuario) {
		return cursosDao.cursosNoMatriculados(usuario)
				.stream()
				.map(c->conversor.cursoToDto(c))
				.collect(Collectors.toList());
	}
	

	
}
