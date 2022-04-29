package model;

import java.util.Date; //La fecha no tiene que estar vinculado a la BBDD
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String nombre;
	private int duracion;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	private double precio;
	@JsonIgnore //No convertir el atributo a JSON
	@ManyToMany(mappedBy="cursos")
    private List<Alumno> alumnos;
	
	//Constructor sin alumnos
	public Curso(int idCurso, String nombre, int duracion, Date fechaInicio, double precio) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.precio = precio;
	}


	
	
	
	

}