package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	private String dni;
	private String nombre;
	private int edad;
	private String email;
	@ManyToOne()
    @JoinColumn(name="curso",referencedColumnName = "idCurso")
	private Curso curso;

}
