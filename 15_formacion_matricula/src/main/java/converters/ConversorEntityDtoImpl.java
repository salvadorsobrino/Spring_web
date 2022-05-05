package converters;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import model.Alumno;
import model.Curso;
import model.Matricula;
import model.MatriculaPk;
@Component //Notacion importante!
public class ConversorEntityDtoImpl implements ConversorEntityDto {

	@Override
	public CursoDto cursoToDto(Curso curso) {
		return new CursoDto(curso.getIdCurso(),curso.getNombre(),curso.getDuracion(),curso.getFechaInicio(),curso.getPrecio());
	}

	@Override
	public Curso dtoToCurso(CursoDto dto) {
		return new Curso(dto.getIdCurso(),dto.getNombre(),dto.getDuracion(),dto.getFechaInicio(),dto.getPrecio());
	}

	@Override
	public AlumnoDto alumnoToDto(Alumno alumno) {
		return new AlumnoDto(alumno.getUsuario(),alumno.getPassword(),alumno.getNombre(),alumno.getEdad(),alumno.getEmail());
	}

	@Override
	public Alumno dtoToAlumno(AlumnoDto dto) {
		return new Alumno(dto.getUsuario(),dto.getPassword(),dto.getNombre(),dto.getEdad(),dto.getEmail());
	}

	@Override
	public MatriculaDto matriculaToDto(Matricula matricula) {
		return new MatriculaDto(matricula.getNota(), cursoToDto(matricula.getCurso()), alumnoToDto(matricula.getAlumno()));
	}

	@Override
	public Matricula dtoToMatricula(MatriculaDto matricula) {
		//return new Matricula(matricula.getNota(), dtoToCurso(matricula.getCursoDto()), dtoToAlumno(matricula.getAlumnoDto()));
		return new Matricula(new MatriculaPk(matricula.getCursoDto().getIdCurso(),matricula.getAlumnoDto().getUsuario()),matricula.getNota(),null,null);
	}

}
