package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity // Entidad de datos que se va a mapear a una base de datos
@Table(name = "alumnos") // Indica nombre de la tabla, a traves del atributo name
public class Alumno {
	@Id // Primary key
	private String usuario;
	private String password;
	private String nombre;
	private int edad;
	private String email;
	
	@OneToMany(mappedBy = "alumno")
	List<Matricula> matriculas;
	
	
	
	//Constructor sin cursos
	public Alumno(String usuario, String password, String nombre, int edad, String email) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
		
	}


	
}
