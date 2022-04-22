package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//delante de cada atributo cada getter y setter si de alguno no queremos
@Entity //Entidad de datos que se va a mapear a una base de datos
@Table(name="alumnos") //Indica nombre de la tabla, a traves del atributo name
public class Alumno {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Llave primaria autogenerada , informa al framework que es una llave primaria autogenerada 
	private int idAlumno;
	private String nombre;
	private String curso;
	private double nota; 
}
