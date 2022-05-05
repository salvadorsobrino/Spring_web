package dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CursoDto {
	private int idCurso;
	private String nombre;
	private int duracion;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechaInicio;
	private double precio;
	//List<String> alumnos;
	
	//Constructor sin id
	public CursoDto(String nombre, int duracion, Date fechaInicio, double precio) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.precio = precio;
	}
	
	

		
}
