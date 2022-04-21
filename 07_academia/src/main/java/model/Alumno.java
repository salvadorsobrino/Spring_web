package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//delante de cada atributo cada getter y setter si de alguno no queremos
public class Alumno {
	private int idAlumno;
	private String nombre;
	private String curso;
	private double nota; 
}
