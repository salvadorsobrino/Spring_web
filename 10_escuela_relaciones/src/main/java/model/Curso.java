package model;

import java.util.Date; //La fecha no tiene que estar vinculado a la BBDD
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name="cursos") //Indica nombre de la tabla, a traves del atributo name
public class Curso {
	@Id //Primary key
	private int idCurso;
	private String denominacion;
	private int duracion;
	private double precio;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@OneToMany(mappedBy="curso")
	private List<Alumno> alumnos;

	public Curso(int idCurso, String denominacion, int duracion, double precio, Date fechaInicio) {
		super();
		this.idCurso = idCurso;
		this.denominacion = denominacion;
		this.duracion = duracion;
		this.precio = precio;
		this.fechaInicio = fechaInicio;
	}
	
	

}
