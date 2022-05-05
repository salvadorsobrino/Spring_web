package dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatriculaDto {
	private double nota;
	@JsonProperty("curso") //Segunda solucion. Alias de la propiedad para la serializacion.
	private CursoDto cursoDto;
	@JsonProperty("alumno")
	private AlumnoDto alumnoDto;

}
