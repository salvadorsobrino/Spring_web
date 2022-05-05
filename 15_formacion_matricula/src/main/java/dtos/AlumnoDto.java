package dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlumnoDto {
	private String usuario;
	private String password;
	private String nombre;
	private int edad;
	private String email;
	//No hace falta una lista de cursos
}
