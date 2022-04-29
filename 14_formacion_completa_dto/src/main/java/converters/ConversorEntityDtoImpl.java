package converters;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dto.AlumnoDto;
import dto.CursoDto;
import model.Alumno;
import model.Curso;
@Component //Notacion importante!
public class ConversorEntityDtoImpl implements ConversorEntityDto {

	@Override
	public CursoDto cursoToDto(Curso curso) {
		return new CursoDto(curso.getIdCurso(),curso.getNombre(),curso.getDuracion(),curso.getFechaInicio(),curso.getPrecio(),
				curso.getAlumnos()
				.stream()
				.map(a->a.getNombre())
				.collect(Collectors.toList()));
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

}
